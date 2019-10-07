<#function link_to path>
    <#return "${siteURL}" + path>
</#function>

<#function to_data num>
    <#return (num * 1000)?number_to_datetime>
</#function>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <#assign fulltitle>
        <#if subtitle??>
            ${subtitle + " | " + title}
        <#else>
            ${title}
        </#if>
    </#assign>

    <title>${fulltitle}</title>
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}">

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
    <link rel="shortcut icon" href="${link_to("/favicon.ico")}">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.10.0/katex.min.css">
    <link rel="stylesheet" href="${link_to("/styles/main.css")}">


    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script src="https://cdn.bootcss.com/highlight.js/9.12.0/highlight.min.js"></script>

    <link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css"/>

</head>