(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-e909f73e"],{"0891":function(t,e,a){e=t.exports=a("2350")(!1),e.push([t.i,"\n.fillcontain {\n  height: calc(100vh - 180px);\n  padding: 30px;\n  overflow: auto;\n}\n.fillcontain .pagination {\n  margin-top: 10px;\n  text-align: right;\n}\n.card-item {\n  line-height: 40px;\n}\n",""])},"5dc7":function(t,e,a){"use strict";a.d(e,"e",function(){return r}),a.d(e,"a",function(){return i}),a.d(e,"c",function(){return o}),a.d(e,"d",function(){return s}),a.d(e,"b",function(){return c}),a.d(e,"f",function(){return u});var n=a("a27e"),l=a("e490");function r(t){return Object(n["a"])({url:l["a"]+"/album/search",method:"post",data:t})}function i(t){return Object(n["a"])({url:l["a"]+"/album/create",method:"post",data:t})}function o(t){return Object(n["a"])({url:l["a"]+"/album/update",method:"post",data:t})}function s(t){return Object(n["a"])({url:l["a"]+"/album/info",method:"post",data:t})}function c(t){return Object(n["a"])({url:l["a"]+"/album/delete",method:"post",data:t})}function u(t){return Object(n["a"])({url:l["a"]+"/album/list",method:"post",data:{}})}},a244:function(t,e,a){var n=a("0891");"string"===typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);var l=a("499e").default;l("1b0352b5",n,!0,{})},a827:function(t,e,a){"use strict";a.r(e);a("96cf");var n=a("3b8d"),l=a("5dc7"),r=a("19b1"),i={data:function(){return{albumDetail:{},loading:!1,tableData:[]}},mounted:function(){var t=Object(n["a"])(regeneratorRuntime.mark(function t(){var e=this;return regeneratorRuntime.wrap(function(t){while(1)switch(t.prev=t.next){case 0:return l["d"]({id:this.$route.query.id}).then(function(t){0==t.errorCode&&(e.albumDetail=t.data)}),t.next=3,this.getMusicList();case 3:case"end":return t.stop()}},t,this)}));function e(){return t.apply(this,arguments)}return e}(),methods:{getMusicList:function(){var t=this;return new Promise(function(e,a){var n=Object.assign({id:t.$route.query.id});t.loading=!0,r["d"](n).then(function(a){0==a.errorCode&&(console.log("---------------------"),console.log(a),t.tableData=a.data),e()}).finally(function(){t.loading=!1})})},toReturn:function(){this.$router.go(-1)}}},o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"fillcontain"},[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v("专辑详情")]),t._v(" "),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"},on:{click:t.toReturn}},[t._v("返回")])],1),t._v(" "),a("el-form",{attrs:{inline:!0,size:"small"}},[a("el-form-item",{attrs:{prop:"pictureUrl",label:"封面",align:"center"}},[a("img",{staticClass:"head_pic",attrs:{src:t.albumDetail.pictureUrl,width:"96",height:"64"}})]),t._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"专辑名称："}},[t._v(t._s(t.albumDetail.name))])],1),t._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"类别："}},[t._v(t._s(t.albumDetail.themeName))])],1)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"歌曲数量："}},[t._v(t._s(t.albumDetail.musicCount))])],1),t._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"歌手："}},[t._v(t._s(t.albumDetail.singerName))])],1)],1),t._v(" "),a("el-form-item",{attrs:{label:"发行时间"}},[t._v("\n        "+t._s(t.albumDetail.publishTime)+"\n      ")]),t._v(" "),a("el-form-item",{attrs:{label:"简介"}},[t._v("\n        "+t._s(t.albumDetail.description)+"\n      ")])],1)],1),t._v(" "),a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v("歌曲列表")])]),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"","highlight-current-row":"","header-cell-class-name":"table-header-class"}},[a("el-table-column",{attrs:{prop:"name",label:"名称",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"fileUrl",label:"歌曲文件",align:"center"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("a",{staticClass:"buttonText",attrs:{href:e.row.fileUrl,target:"_blank"}},[t._v(t._s(e.row.filePath))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"timeLengthText",label:"歌曲时长",align:"center"}}),t._v(" "),a("el-table-column",{attrs:{prop:"fileSizeText",label:"文件大小",align:"center"}})],1)],1)],1)},s=[],c=a("2455");function u(t){a("a244")}var d=!1,m=u,f=null,b=null,p=Object(c["a"])(i,o,s,d,m,f,b);e["default"]=p.exports}}]);