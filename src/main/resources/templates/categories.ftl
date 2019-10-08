<#assign subtitle = "分类列表">

<#include "header.ftl">

<body>
<div id="app" class="main">

    <#include "sidebar.ftl">

    <div class="main-container">
        <div class="content-container" data-aos="fade-up">
            <h2 class="tag-list-title">分类列表</h2>
            <div class="tags-container">

                <#list categories as category>
                    <a class="tag" href="${link_to("/category/" + category.slug)}">${category.name}</a>
                </#list>

            </div>
        </div>
    </div>
</div>

<#include "footer.ftl">