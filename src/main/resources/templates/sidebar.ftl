<div class="sidebar" :class="{ 'full-height': menuVisible }">
    <div class="top-container" data-aos="fade-right">
        <div class="top-header-container">
            <a class="site-title-container" href="${link_to("/")}">
                <img src="https://wuyuncheng.com/images/avatar.png?v=1570268731872" class="site-logo">
                <h1 class="site-title">${title}</h1>
            </a>
            <div class="menu-btn" @click="menuVisible = !menuVisible">
                <div class="line"></div>
            </div>
        </div>
        <div>
            ${navbar}
        </div>
    </div>
</div>