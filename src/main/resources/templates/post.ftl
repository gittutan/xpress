<#assign subtitle = post.title>

<#include "header.ftl">

<body>
<div id="app" class="main">

    <#include "sidebar.ftl">

    <div class="main-container">
        <div class="content-container" data-aos="fade-up">

            <div class="post-detail">
                <h2 class="post-title">${post.title}</h2>
                <div class="post-date">${to_data(post.modified)}</div>

                <div class="post-content">
                    ${post.content}
                </div>

                <#if tags??>
                    <div class="tag-container">
                        <#list tags as tag>
                            <a href="${link_to("/tag/" + tag.slug)}" class="tag">
                                ${tag.name}
                            </a>
                        </#list>
                    </div>
                </#if>

            </div>
        </div>
    </div>
</div>

<#include "footer.ftl">