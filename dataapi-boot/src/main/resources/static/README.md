# Macro_Fore_End
## 项目文件作用说明
- assets《资源目录》
该目录存放一些资源，如字体、图片、音频、视频等
> images  图片
> 
- css：《样式目录》
css文件是通过less语法生成；开发时请创建less文件，自动生成对应的css文件。
> data_center 
> > 导航栏 数据中心 的css文件
> 
> index
> > 导航栏 首页 的css文件
> 
> login
> > 登录、注册 的css文件
> 
> middle_ware
> > 导航栏 设备适配 的css文件
> 
> quote
> > 引用的第三方css文件
> 
> fonts 
> > 字体
> 
> base.less、base.css
> > 每个页面必须引用的基础css文件
> 
- html：《页面目录》
> data_center 
> > 导航栏 数据中心 的html文件
> 
> index
> > 导航栏 首页 的html文件
> 
> middle_ware
> > 导航栏 设备适配 的html文件
> 
> occupy
> > 样例文件：当创建一个新的导航栏时，可以参考这个目录
- json：《模拟数据目录》
存放模拟的数据，即数据格式，与后端进行交互使用
- js：《逻辑目录》
css文件是通过less语法生成；开发时请创建less文件，自动生成对应的css文件。
> data_center 
> > 导航栏 数据中心 的js文件
> 
> index
> > 导航栏 首页 的js文件
> 
> login
> > 登录、注册 的js文件
> 
> middle_ware
> > 导航栏 设备适配 的css文件
> 
> quote
> > 引用的第三方js文件
> 
> base.js
> > 每个页面必须引用的基础js文件
> 
> http.js
> > 进行http请求的js文件，对http进行封装
> 
> myEchart.js
> > 定义折线图类，方便绘制折线图
> 
> nav.js
> > 整个项目导航栏的js文件
> 
- login.html：登录页面文件
- register.html：注册页面文件

## 项目整合到spring-boot
	1、html文件夹下的所有有导航的文件，把绝对地址"/iot-platform"删除
	2、js文件夹中的base.js中方法：getClassification中的website_path中的"/iot-platform"去掉
	3、js文件夹中的http.js中的baseurl后面不加任何的标记，把"/api" 去掉。

## 项目版本更新说明
#### 2021/05/20 { lvyalong }
	1、数据可视化页面的完善【数据为假数据】
	2、数据中心结构修改，导航栏的修改

#### 2021/05/20 { lvyalong }
	1、规则引擎首页完善
		获取规则列表、详情跳转、删除规则、控制规则的开关
		未完成：搜索规则
		
#### 2021/05/18 { lvyalong }
	1、规则引擎首页的编写。
		当前完成：获取规则列表、详情跳转；
		未完成：搜索规则、删除规则、控制规则的开关、【需要后台修改ntid的类型为string】

#### 2021/04/17 { lvyalong }
	1、修复浏览器像素低，侧边的页面导航栏无法显示的问题[解决方案：将页面导航栏上方修改为下拉菜单]
	2、修改项目最小像素为800px
	3、修改页面的路径，与dataapi-boot中同步
	
#### 2021/03/26 { YanJing }
	1、新增登录页面、注册页面、首页

#### 2021/03/19 { lvyalong }
	1、合并sjfei分支，解决冲突
	2、合并登录和注册页面
	3、修复导航栏bug问题：滚动条有滚动距离时，导航栏的初始位置有问题
	4、添加数据可视化组件，并固定显示历史数据

