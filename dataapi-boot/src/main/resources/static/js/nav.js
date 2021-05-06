/**
 * 导航类
 */
class Nav {
	/**
	 * @param {String} navTag 导航父类的标签名
	 * @param {String} navId 导航父类的标签Id
	 * @param {String} navItemTag 具体实现导航的标签名
	 * @param {Object} context 导航的上下文
	 */
	constructor(navTag, navId, navItemTag, context = window) {
		let THIS = this;
		THIS.navTag = navTag;
		THIS.navId = navId;
		THIS.navItemTag = navItemTag;

		THIS.context = context;

		THIS.style = {}; // 导航栏中相关的样式

		THIS.initNav();
	}

	/**
	 * 初始化
	 */
	initNav() {
		let THIS = this;
		let navElem = $(THIS.navTag) // 导航栏
		let marginTopBase = navElem.css("marginTop") // margin的基准
		marginTopBase = marginTopBase.substr(0, marginTopBase.length - 2);
		let marginTop = marginTopBase; // 记录之前的marginTop的值，防止没有置顶
		Object.assign(THIS.style, {
			marginTopBase,
			marginTop
		})

		if (window.pageYOffset >= 0) {
			// 设置margin的值，防止滚动过快没有效果，需要判断marginTop的值
			let margin_now = marginTopBase - window.pageYOffset / 5; // 计算当前的marginTop的值
			navElem.css("margin", margin_now + "px " + 2 * margin_now + "px")
			if (margin_now >= 0) {
				navElem.css("margin", margin_now + "px " + 2 * margin_now + "px")
			} else {
				margin_now = 0;
				navElem.css("margin", margin_now + "px " + 2 * margin_now + "px")
			}
			THIS.style.marginTop = margin_now;
			// 设置margin-radius的值
			if (margin_now == 0) {
				navElem.css("box-shadow", "0");
				navElem.css("border-radius", "0px");
			} else if (margin_now > 0) {
				navElem.css("box-shadow", "0 0 10px #000000");
				navElem.css("border-radius", "5px")
			}
		}

	}

	/**
	 * 页面滚动，控制导航栏样式的变化【主要是提供给子页面进行使用】
	 * @param {Object} scrollingElement
	 */
	scroll(event) {
		let THIS = this.nav; // 获取到本class
		let navElem = $(THIS.navTag) // 导航栏
		let margin_now = THIS.style.marginTopBase - event.target.scrollingElement.scrollTop / 5; // 计算当前的marginTop的值

		// 设置margin的值，防止滚动过快没有效果，需要判断marginTop的值
		if (margin_now >= 0) {
			navElem.css("margin", margin_now + "px " + 2 * margin_now + "px")
		} else if (THIS.style.marginTop > 0) {
			margin_now = 0;
			navElem.css("margin", margin_now + "px " + 2 * margin_now + "px")
		}
		THIS.style.marginTop = margin_now;

		// 设置margin-radius的值
		if (margin_now == 0) {
			navElem.css("box-shadow", "0");
			navElem.css("border-radius", "0px");
		} else if (margin_now > 0) {
			navElem.css("box-shadow", "0 0 10px #000000");
			navElem.css("border-radius", "5px")
		}


	}
}
