(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a3472eb6"],{"7b71":function(t,e,n){"use strict";var r=n("cd06"),a=n.n(r);a.a},b3e7:function(t,e,n){"use strict";n.r(e);var r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("el-button",{staticClass:"new-category",attrs:{type:"primary"},on:{click:t.newCategory}},[t._v("新建分类")]),t._v(" "),n("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.listLoading,expression:"listLoading"}],staticStyle:{width:"100%"},attrs:{data:t.categories,border:"",fit:"","highlight-current-row":""}},[n("el-table-column",{attrs:{align:"center",label:"ID",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){var r=e.row;return[n("span",[t._v(t._s(r.id))])]}}])}),t._v(" "),n("el-table-column",{attrs:{width:"120px",align:"center",label:"名称"},scopedSlots:t._u([{key:"default",fn:function(e){var r=e.row;return[n("span",[t._v(t._s(r.name))])]}}])}),t._v(" "),n("el-table-column",{attrs:{width:"120px",align:"center",label:"缩略名"},scopedSlots:t._u([{key:"default",fn:function(e){var r=e.row;return[n("span",[t._v(t._s(r.slug))])]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center",label:"文章数量",width:"110"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return[t._v("\n        "+t._s(n.count)+"\n      ")]}}])}),t._v(" "),n("el-table-column",{attrs:{"min-width":"300px",label:"描述"},scopedSlots:t._u([{key:"default",fn:function(e){var r=e.row;return[n("span",[t._v(t._s(r.description))])]}}])}),t._v(" "),n("el-table-column",{attrs:{align:"center",label:"操作",width:"230","class-name":"small-padding fixed-width"},scopedSlots:t._u([{key:"default",fn:function(e){var r=e.row;return[n("router-link",{attrs:{to:"/category/edit/"+r.id}},[n("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-edit"}},[t._v("\n            编辑\n          ")])],1),t._v(" "),n("el-button",{attrs:{type:"danger",size:"mini",icon:"el-icon-delete"},on:{click:function(e){return t.delCategory(r.id)}}},[t._v("\n          删除\n        ")])]}}])})],1)],1)},a=[],i=n("c405"),o={name:"ManageCategory",data:function(){return{listLoading:!0,categories:void 0}},created:function(){var t=this;Object(i["b"])().then((function(e){t.listLoading=!0,t.categories=e,t.listLoading=!1}))},methods:{newCategory:function(){this.$router.push("/category/create")},delCategory:function(t){var e=this;this.$confirm("此操作将永久该分类, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){Object(i["d"])(t).then((function(){e.$message({showClose:!0,message:"分类删除成功",type:"success"}),e.categories=e.categories.filter((function(e){return e.id!==t}))}))})).catch((function(){}))}}},c=o,u=(n("7b71"),n("6691")),l=Object(u["a"])(c,r,a,!1,null,"42f047f4",null);e["default"]=l.exports},c405:function(t,e,n){"use strict";n.d(e,"b",(function(){return a})),n.d(e,"c",(function(){return i})),n.d(e,"a",(function(){return o})),n.d(e,"e",(function(){return c})),n.d(e,"d",(function(){return u}));var r=n("b775");function a(){return Object(r["a"])({url:"/categories",method:"get"})}function i(t){return Object(r["a"])({url:"/categories/"+t,method:"get"})}function o(t){return Object(r["a"])({url:"/categories",method:"post",data:t})}function c(t,e){return Object(r["a"])({url:"/categories/"+t,method:"put",data:e})}function u(t){return Object(r["a"])({url:"/categories/"+t,method:"delete"})}},cd06:function(t,e,n){}}]);