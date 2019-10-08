<#assign subtitle = "标签列表">

<#include "header.ftl">

    <body>
<div id="app" class="main">

    <#include "sidebar.ftl">

    <div class="main-container">
        <div class="content-container" data-aos="fade-up">
            <h2 class="tag-list-title">标签列表</h2>
            <div class="tags-container">

                <#list tags as tag>
                    <a class="tag" href="${link_to("/tag/" + tag.slug)}">${tag.name}</a>
                </#list>

            </div>
        </div>
    </div>
</div>

<#include "footer.ftl">