<script src="https://unpkg.com/aos@next/dist/aos.js"></script>

<script type="application/javascript">

    AOS.init();

    hljs.initHighlightingOnLoad()

    var app = new Vue({
        el: '#app',
        data: {
            menuVisible: false,
            formData: {
                postId: <#if post??>${post.postId}<#else>${"undefined"}</#if>,
                author: undefined,
                mail: undefined,
                url: undefined,
                content: undefined
            }
        },
        methods: {
            onSubmit() {
                axios
                    .post("${siteURL}/comment",
                        this.formData,
                        {
                            headers: { 'content-type': 'application/json;charset=UTF-8' }
                        })
                    .then(response => {
                        alert(response.data.message)
                    })
                    .catch(err => {
                        console.log(err)
                        alert(err.response.data.message)
                    })
            }
        }
    })

</script>

</body>
</html>