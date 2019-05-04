# DeveloperRepository 开发者的工具类库

**一、自定义 View**

1、MaterialEditView

遇到的问题：Cannot resolve symbol AppCompatActivity

解决方法：File -> Invalidate Caches / Restart

参考：
https://stackoverflow.com/questions/30803405/cannot-resolve-symbol-appcompatactivity-support-v7-libraries-arent-recognized

2、SquareImageView

继承已有的 View，重写 onMeasure()