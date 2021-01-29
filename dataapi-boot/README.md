# xidianiot/dataapi

1. dataapi模块对应原平台yunjian、wagang模块
2. dataapi模块提供最基本的可视化界面，包括：场景、节点、属性等增删查改；规则引擎配置
3. dataapi模块采用完全前后端分离的模式，REST框架使用jersey

拦截所有请求、验证token、在OauthConfig配置、不验证直接注释掉即可。