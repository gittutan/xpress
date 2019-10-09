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

                <#list comments as comment>
                    <div class="comment-area">
                        <div class="comment-author">${comment.author} 说：</div>
                        <div class="comment-content">${comment.content}</div>
                        <div class="comment-time">${to_data(comment.created)}</div>
                    </div>
                </#list>

                <#if post.isAllowComments>
                    <h3 style="margin: 30px 0 10px 0">提交评论：</h3>
                    <div class="form-comment">
                        <div class="field-comment">
                            <div class="user-info-comment">
                                <label for="id-author">名字：</label>
                                <div class="input-comment">
                                    <input id="id-author" name="author" type="text" v-model="formData.author" />
                                </div>
                                <label for="id-mail">邮箱：</label>
                                <div class="input-comment">
                                    <input id="id-mail" name="mail" type="text" v-model="formData.mail" />
                                </div>
                                <label for="id-url">网站：</label>
                                <div class="input-comment">
                                    <input id="id-url" name="url" type="text" v-model="formData.url" />
                                </div>
                            </div>
                            <div class="message-comment">
                                <label for="id-content">内容：</label>
                                <textarea id="id-content" name="content" rows="5" v-model="formData.content"></textarea>
                            </div>
                        </div>
                        <div class="button-comment">
                            <button class="active" @click="onSubmit">
                                提交
                            </button>
                        </div>
                    </div>
                </#if>

            </div>

        </div>
    </div>
</div>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<#include "footer.ftl">