webpackJsonp([16],{GPHE:function(t,e){},i3CB:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});s("hpcr"),s("62KO");var o=s("Y52p"),n=(s("rGqP"),s("7Ah8"),s("odqc"),s("Znkm"),s("ALGc"),s("gHqA")),i=(new n.a,o.a,null),a=new n.a,r={name:"register",data:function(){return{holdPhone:this.$t("register.holdPhone"),holdNewPassword:this.$t("register.holdNewPassword"),inputVerificationCode:this.$t("register.inputVerificationCode"),isMsgShow:!1,countDown:120,sjtLogo:"../../../static/images/mine/sjtLogin.png",sjtLogo2:"../../../static/images/mine/sjtLogo1.jpg",iconType:"",register:{telephone:null,identifyCode:null,passwords:null},isUserInsert:!0,comparePollCodeData:null,userNo:null}},components:{XImg:o.a},mounted:function(){this.initData()},methods:{initData:function(){},open:function(t){this.$router.openPage(t)},getMsgCode:function(){var t=this;this.register.telephone?(this.isMsgShow=!0,i=setInterval(function(){t.countDown--,t.countDown<=0&&(clearInterval(i),t.isMsgShow=!1,t.countDown=120)},1e3),this.findUserByTelephoneInWeb()):this.$vux.toast.show({text:"请填写手机号！",type:"text"})},userRegister:function(){this.register.telephone||this.register.passwords||this.register.identifyCode?this.comparePollCode():this.$vux.toast.show({text:"请填写正确的用户信息！",type:"cancel"})},findUserByTelephoneInWeb:function(){var t=this;this.$http.get("/userRegister/findUserByTelephone",{params:{telephone:this.register.telephone}}).then(function(e){if(200==e.status&&"00000"==e.data.rspCode){if(console.log(e.data.data),console.log("根据手机号码查找用户移动端"),!e.data.data)return t.$vux.toast.show({text:"用户不存在，请去注册",type:"text"}),clearInterval(i),t.isMsgShow=!1,t.countDown=120,!1;console.log("已经存在的用户"),t.createPollCode()}}).catch(function(t){console.log(t)})},findUserByTelephoneInWx:function(){},createPollCode:function(){var t=this;this.$http.get("/userRegister/createPollCode",{params:{telephone:this.register.telephone}}).then(function(e){console.log("发送短信成功"),200==e.status&&(console.log(e.data),e.data&&t.$vux.toast.show({text:"信息已发送,请注意查收！",type:"text"}))}).catch(function(t){console.log(t)})},comparePollCode:function(){var t=this;this.comparePollCodeData=null,this.$http.post("/userRegister/comparePollCode",{telephone:this.register.telephone,identifyCode:this.register.identifyCode}).then(function(e){if(console.log("比较验证码成功"),console.log(e.data),200==e.status){if("en"==a.getItem("localLang").toString())var s=e.data.usErrorMsg;else s=e.data.cnErrorMsg;switch(e.data.rspCode){case"00000":t.comparePollCodeData=1;break;default:t.$vux.toast.show({text:s,type:"text"})}console.log(t.comparePollCodeData),console.log("this.comparePollCodeData"),t.comparePollCodeData&&t.modifyUserPassword()}}).catch(function(t){console.log(t)})},modifyUserPassword:function(){var t=this;this.$http.get("/userRegister/modifyUserPassword",{params:{telephone:this.register.telephone,userPassword:this.register.passwords}}).then(function(e){console.log(e),200==e.status&&"00000"==e.data.rspCode&&(t.$vux.toast.show({text:"登录成功！",type:"text"}),t.findUserByTelephoneEnd(),setTimeout(function(){t.$router.openPage("/closeAccount")},1e3))}).catch(function(t){console.log(t)})},bindOpenidTel:function(){},insertTel:function(){},findUserByTelephoneEnd:function(){this.$http.get("/userRegister/findUserByTelephone",{params:{telephone:this.register.telephone}}).then(function(t){if(200==t.status&&"00000"==t.data.rspCode&&(console.log("根据手机号码查找用户,确定用户已经存在了，存储userNo,telephone"),console.log(t.data.data),t.data.data)){var e={telephone:t.data.data.telephone,userNo:t.data.data.userNo,weixinOpenid:t.data.data.weixinOpenid};console.log("telUserNo"),console.log(e),a.setItem("telUserNo",e)}}).catch(function(t){console.log(t)})}}},l={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"app-init"},[s("x-img",{directives:[{name:"lazy",rawName:"v-lazy",value:t.sjtLogo,expression:"sjtLogo"}],staticClass:"sjtLogin-bImg"}),t._v(" "),s("div",{staticClass:"sjtLogin-cont"},[s("div",{staticClass:"sjtlc-logo"},[s("x-img",{directives:[{name:"lazy",rawName:"v-lazy",value:t.sjtLogo2,expression:"sjtLogo2"}],staticClass:"sjtlcl-img"}),t._v(" "),s("div",[t._v("秦安电商")])],1),t._v(" "),s("div",{staticClass:"sjtlc-title"},[t._v("\n\t\t\t"+t._s(t.$t("forgetPassword.forgetPassword"))+"\n\t\t")]),t._v(" "),s("div",{staticClass:"sjtlc-cont"},[s("group",{staticClass:"sjtlc-group"},[s("x-input",{staticClass:"gInput",attrs:{placeholder:t.holdPhone,"placeholder-align":"left"},model:{value:t.register.telephone,callback:function(e){t.$set(t.register,"telephone",e)},expression:"register.telephone"}})],1),t._v(" "),s("group",{staticClass:"sjtlc-group"},[s("x-input",{staticClass:"gInput",attrs:{placeholder:t.inputVerificationCode,"placeholder-align":"left"},model:{value:t.register.identifyCode,callback:function(e){t.$set(t.register,"identifyCode",e)},expression:"register.identifyCode"}},[t.isMsgShow?s("x-button",{staticClass:"countDown",attrs:{slot:"right",type:"default",mini:"",disabled:""},slot:"right"},[t._v("\n\t\t\t\t\t\t"+t._s(t.$t("register.regain"))+"("+t._s(t.countDown)+")\n\t\t\t\t\t")]):s("x-button",{attrs:{slot:"right",type:"primary",mini:""},nativeOn:{click:function(e){return t.getMsgCode(e)}},slot:"right"},[t._v(t._s(t.$t("register.getCode")))])],1)],1),t._v(" "),s("group",{staticClass:"sjtlc-group"},[s("x-input",{staticClass:"gInput",attrs:{placeholder:t.holdNewPassword},model:{value:t.register.passwords,callback:function(e){t.$set(t.register,"passwords",e)},expression:"register.passwords"}})],1),t._v(" "),s("div",{staticClass:"sjtlc-foot"},[s("div",{on:{click:function(e){t.open("/login")}}},[t._v(t._s(t.$t("register.HavePasswordTologin")))])])],1),t._v(" "),s("div",{staticClass:"sjtlc-btn"},[s("x-button",{staticClass:"login-btn",attrs:{type:"primary"},nativeOn:{click:function(e){t.userRegister()}}},[t._v(t._s(t.$t("login.login")))])],1)])],1)},staticRenderFns:[]};var c=s("VU/8")(r,l,!1,function(t){s("GPHE")},null,null);e.default=c.exports}});
//# sourceMappingURL=16.262856b3893a83dd9303.js.map