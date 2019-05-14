# DeveloperRepository 开发者仓库

- 学习笔记
- 工具类库

**一、自定义 View**

1、MaterialEditView

遇到的问题：Cannot resolve symbol AppCompatActivity

解决方法：File -> Invalidate Caches / Restart

参考：
https://stackoverflow.com/questions/30803405/cannot-resolve-symbol-appcompatactivity-support-v7-libraries-arent-recognized

2、SquareImageView

继承已有的 View，重写 onMeasure()

3、CircleView

继承自 View，完全进行自定义计算，重写 onMeasure()，用 resolveSize() / resolveSizeAndState() 修正结果。

4、TagLayout

自定义 Layout，继承自 ViewGroup，重写 onMeasure()、onLayout()

注意：通过 measureChildWithMargins 测量子 View 时，需要重写 generateLayoutParams() 方法，并返回
MarginLayoutParams 对象。

5、TouchView

自定义 View 的触摸反馈，重写 onTouchEvent() 方法。

6、ScalableImageView

通过 GestureDetector 和 OverScroller 实现双击放大并支持双向滑动的 View。

7、多点触控

- 接力型：RelayMultiTouchView
- 配合型：CooperateMultiTouchView

**二、事件分发**

1、原理

参考：[Android事件分发机制 详解攻略，您值得拥有](https://blog.csdn.net/carson_ho/article/details/54136311)

2、Demo

ViewGroupDispatchActivity、SubView、SubViewGroup