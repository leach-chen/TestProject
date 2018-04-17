package com.leachchen.testmarkdown;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.leachchen.testmarkdown.R;
import com.leachchen.testmarkdown.markdown1.Check;
import com.leachchen.testmarkdown.markdown1.ExpandableLinearLayout;
import com.leachchen.testmarkdown.markdown1.Markdown1PreviewActivity;
import com.leachchen.testmarkdown.markdown1.PerformEditable;
import com.leachchen.testmarkdown.markdown1.TabIconView;

import java.io.File;

public class Markdown1Activity extends AppCompatActivity implements View.OnClickListener{

    private final int SYSTEM_GALLERY = 1;
    protected Toolbar mToolbar;
    protected ExpandableLinearLayout mExpandLayout;

    private EditText et_content;

    private TabIconView mTabIconView;
    protected AppBarLayout mAppBar;
    private MenuItem mActionOtherOperate;

    private PerformEditable mPerformEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_markdown1);

        mAppBar = (AppBarLayout)this.findViewById(R.id.id_appbar);
        mToolbar = (Toolbar)this.findViewById(R.id.id_toolbar);
        mExpandLayout = (ExpandableLinearLayout)this.findViewById(R.id.action_other_operate);
        mTabIconView = (TabIconView)this.findViewById(R.id.tabIconView);
        et_content = (EditText) this.findViewById(R.id.et_content);

        mPerformEditable = new PerformEditable(et_content);

        setSupportActionBar(mToolbar);
        if (Build.VERSION.SDK_INT >= 21) {
            this.mAppBar.setElevation(0f);
        }

        mTabIconView.addTab(R.drawable.ic_shortcut_format_list_bulleted, R.id.id_shortcut_list_bulleted, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_list_numbers, R.id.id_shortcut_format_numbers, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_insert_link, R.id.id_shortcut_insert_link, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_insert_photo, R.id.id_shortcut_insert_photo, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_console, R.id.id_shortcut_console, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_bold, R.id.id_shortcut_format_bold, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_italic, R.id.id_shortcut_format_italic, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_1, R.id.id_shortcut_format_header_1, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_2, R.id.id_shortcut_format_header_2, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_3, R.id.id_shortcut_format_header_3, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_quote, R.id.id_shortcut_format_quote, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_xml, R.id.id_shortcut_xml, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_minus, R.id.id_shortcut_minus, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_strikethrough, R.id.id_shortcut_format_strikethrough, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_grid, R.id.id_shortcut_grid, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_4, R.id.id_shortcut_format_header_4, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_5, R.id.id_shortcut_format_header_5, this);
        mTabIconView.addTab(R.drawable.ic_shortcut_format_header_6, R.id.id_shortcut_format_header_6, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_other_operate://展开和收缩
                if (!mExpandLayout.isExpanded())
                    //没有展开，但是接下来就是展开，设置向上箭头
                    mActionOtherOperate.setIcon(R.drawable.ic_arrow_up);
                else
                    mActionOtherOperate.setIcon(R.drawable.ic_add_white_24dp);
                mExpandLayout.toggle();
                return true;
            case R.id.action_preview:
                Intent intent = new Intent(this, Markdown1PreviewActivity.class);
                intent.putExtra(Markdown1PreviewActivity.PREVIEW_MARKDOWN,et_content.getText().toString());
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor_act, menu);
        mActionOtherOperate = menu.findItem(R.id.action_other_operate);
        if (mExpandLayout.isExpanded())
            //展开，设置向上箭头
            mActionOtherOperate.setIcon(R.drawable.ic_arrow_up);
        else
            mActionOtherOperate.setIcon(R.drawable.ic_add_white_24dp);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        if (R.id.id_shortcut_insert_photo == v.getId()) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);// Pick an item fromthe
            intent.setType("image/*");// 从所有图片中进行选择
            startActivityForResult(intent, SYSTEM_GALLERY);
            return;
        } else if (R.id.id_shortcut_insert_link == v.getId()) {
            //插入链接
            insertLink();
            return;
        } else if (R.id.id_shortcut_grid == v.getId()) {
            //插入表格
            insertTable();
            return;
        }
        mPerformEditable.onClick(v);
    }


    /**
     * 插入表格
     */
    private void insertTable() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.view_common_input_table_view, null);

        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("插入表格")
                .setView(rootView)
                .show();

        final TextInputLayout rowNumberHint = (TextInputLayout) rootView.findViewById(R.id.rowNumberHint);
        final TextInputLayout columnNumberHint = (TextInputLayout) rootView.findViewById(R.id.columnNumberHint);
        final EditText rowNumber = (EditText) rootView.findViewById(R.id.rowNumber);
        final EditText columnNumber = (EditText) rootView.findViewById(R.id.columnNumber);


        rootView.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rowNumberStr = rowNumber.getText().toString().trim();
                String columnNumberStr = columnNumber.getText().toString().trim();

                if (Check.isEmpty(rowNumberStr)) {
                    rowNumberHint.setError("不能为空");
                    return;
                }
                if (Check.isEmpty(columnNumberStr)) {
                    columnNumberHint.setError("不能为空");
                    return;
                }


                if (rowNumberHint.isErrorEnabled())
                    rowNumberHint.setErrorEnabled(false);
                if (columnNumberHint.isErrorEnabled())
                    columnNumberHint.setErrorEnabled(false);

                mPerformEditable.perform(R.id.id_shortcut_grid, Integer.parseInt(rowNumberStr), Integer.parseInt(columnNumberStr));
                dialog.dismiss();
            }
        });

        rootView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    /**
     * 插入链接
     */
    private void insertLink() {
        View rootView = LayoutInflater.from(this).inflate(R.layout.view_common_input_link_view, null);

        final AlertDialog dialog = new AlertDialog.Builder(this, R.style.DialogTheme)
                .setTitle("插入链接")
                .setView(rootView)
                .show();

        final TextInputLayout titleHint = (TextInputLayout) rootView.findViewById(R.id.inputNameHint);
        final TextInputLayout linkHint = (TextInputLayout) rootView.findViewById(R.id.inputHint);
        final EditText title = (EditText) rootView.findViewById(R.id.name);
        final EditText link = (EditText) rootView.findViewById(R.id.text);


        rootView.findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleStr = title.getText().toString().trim();
                String linkStr = link.getText().toString().trim();

                if (Check.isEmpty(titleStr)) {
                    titleHint.setError("不能为空");
                    return;
                }
                if (Check.isEmpty(linkStr)) {
                    linkHint.setError("不能为空");
                    return;
                }

                if (titleHint.isErrorEnabled())
                    titleHint.setErrorEnabled(false);
                if (linkHint.isErrorEnabled())
                    linkHint.setErrorEnabled(false);

                mPerformEditable.perform(R.id.id_shortcut_insert_link, titleStr, linkStr);
                dialog.dismiss();
            }
        });

        rootView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == SYSTEM_GALLERY) {
            Uri uri = data.getData();
            String[] pojo = {MediaStore.Images.Media.DATA};
            Cursor cursor = this.managedQuery(uri, pojo, null, null, null);
            if (cursor != null) {
//                    ContentResolver cr = this.getContentResolver();
                int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(colunm_index);
                //以上代码获取图片路径
                Uri.fromFile(new File(path));//Uri.decode(imageUri.toString())
                mPerformEditable.perform(R.id.id_shortcut_insert_photo, Uri.fromFile(new File(path)));
            } else {
                Toast.makeText(this, "图片处理失败",Toast.LENGTH_SHORT).show();
            }
        }

    }


}
