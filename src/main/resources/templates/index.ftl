<#include "header.ftl">

<#assign posts = page.records>
<#assign pageCount = (page.total / page.size)?ceiling>

<body>
<div id="app" class="main">

    <#include "sidebar.ftl">

    <div class="main-container">
        <div class="content-container" data-aos="fade-up">

            <#list posts as post>

                <#if post.slug??>
                    <#assign url = link_to("/post/" + post.slug)>
                <#else>
                    <#assign url = link_to("/post/" + post.postId)>
                </#if>

                <article class="post-item">

                    <div class="left">
                        <a href="${url}">
                            <h2 class="post-title">${post.title}</h2>
                        </a>
                        <div class="post-date">
                            ${to_data(post.modified)}
                        </div>
                        <div class="post-abstract">
                        </div>
                    </div>

                </article>
            </#list>

            <#if pageCount gt page.current>
                <div class="pagination-container">
                    <a href="${link_to("/page/" + (page.current + 1))}" class="next">下一页 <i class="icon-arrow-ios-forward-outline"></i></a>
                </div>
            </#if>

        </div>
    </div>
</div>

<#include "footer.ftl">