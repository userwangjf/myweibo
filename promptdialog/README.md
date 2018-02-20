# 带输入功能的对话框

#Screenshot

![这里写图片描述](./gif/show.gif)

## Usage

```java
    EditDialog editDialog = new EditDialog(this);
    editDialog.show();
    editDialog.setOnPosNegClickListener(new EditDialog.OnPosNegClickListener() {
        @Override
        public void posClickListener(String value) {
            Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
            mTv.setText(value);
        }

        @Override
        public void negCliclListener(String value) {
            Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
            mTv.setText(value);
        }
    });
```


