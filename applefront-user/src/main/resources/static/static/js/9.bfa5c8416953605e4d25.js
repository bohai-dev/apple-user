webpackJsonp([9],{YKCt:function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=o("hpcr"),a=o("62KO"),i=o("Y52p"),n=(o("rGqP"),o("7Ah8")),r=o("odqc"),l=o("Znkm"),c=o("ALGc"),d=o("gHqA"),u=o("bjBg"),g=o("Zrlr"),h=o.n(g),m=o("wxAW"),p=o.n(m),f=function(){function t(){h()(this,t)}return p()(t,[{key:"isMobile",value:function(){for(var t=navigator.userAgent,e=["Android","iPhone","SymbianOS","Windows Phone","iPad","iPod"],o=!1,s=0;s<e.length;s++)if(t.indexOf(e[s])>0){o=!0;break}return o}}]),t}(),v=function(t){return(t=t.toString())[1]?t:"0"+t},y={ftYMDHMS:function(t){var e=t.getFullYear(),o=t.getMonth()+1,s=t.getDate(),a=t.getHours(),i=t.getMinutes(),n=t.getSeconds();return[e,o,s].map(v).join("/")+" "+[a,i,n].map(v).join(":")},ftNoHMS:function(t){var e=t.getFullYear(),o=t.getMonth()+1,s=t.getDate();t.getHours(),t.getMinutes(),t.getSeconds();return[e,o,s].map(v).join("/")}};o("M4fF"),new d.a;a.a,s.a,i.a,n.a,r.a,l.a,c.a;var P=new d.a,A=null,x=1,w=null,S={};function O(){var t=(new Date).getTime();WeixinJSBridge.invoke("getBrandWCPayRequest",{appId:S.appid,timeStamp:t,nonceStr:S.nonce_str,package:"prepay_id="+S.prepay_id,signType:"MD5",paySign:S.sign},function(t){console.log("onBridgeReady"),console.log(t),t.err_msg})}var I={name:"closeAccount",data:function(){return{showHideOnBlur:!1,showPositionValue:!1,position:"default",headTitle:this.$t("closeAccount.submitOrder"),mustAddress:this.$t("closeAccount.MustAddress"),invitethephone:this.$t("closeAccount.Invitethephone"),appointmentTime:this.$t("closeAccount.appointmentTime"),modeOfPayment:this.$t("closeAccount.modeOfPayment"),theSellerMessage:this.$t("closeAccount.theSellerMessage"),holdSay:this.$t("closeAccount.holdSay"),enjoyLooking:this.$t("closeAccount.enjoyLooking"),checkerItems:[{key:"0",value:this.$t("closeAccount.reserve")},{key:"1",value:this.$t("closeAccount.eatIn")}],checkerDemo:{key:"1",value:this.$t("closeAccount.eatIn")},demo1:{key:"1",value:this.$t("closeAccount.eatIn")},demo2:{key:"0",value:this.$t("closeAccount.reserve")},demo3:{key:"2",value:this.$t("closeAccount.takeOut")},takeOut:this.$t("closeAccount.takeOut"),modeDistribution:this.$t("closeAccount.modeDistribution"),gdTitle:"",sjtLogo:"../../../static/images/mine/sjtLogo1.jpg",sjtStripeLogo:"../../../static/images/mine/circleLogo.png",listPc:[["VISA/Master Card","微信支付","支付宝"]],listMo:[["VISA/Master Card"]],listWx:[["微信支付"]],isPcMoWx:"",listSel:[["VISA/Master Card"]],valueDefault:["微信支付"],pcPayUrl:"",iotPayMsg:"",showPhone:!1,showPhoneTitle:this.$t("closeAccount.changePhone"),formatDemoValue:[],ppAllYuyueTime:[],isShowOrderTime:!1,format:function(t,e){return console.log(t),"closeDoor"==t[1]?""+t[0]:t[0]+":"+t[1]},allGoods:[],textAreaValue:"",promotionId:null,promotionName:null,storeAddress:"shanghai",storePhone:"110",showLoading:!1,showInitData:!1,showText:this.$t("reminder.dataLoading"),orderNo:null,goodsId:null,maxNum:250,userAddr:{acceptName:"请填写用户收获地址信息！",acceptTel:"",accessAddress:"",defaultAddress:!1}}},components:{Confirm:a.a,headerBack:s.a,XImg:i.a,PopupPicker:n.a,Tab:r.a,TabItem:l.a,XTextarea:c.a},mounted:function(){var t=this,e=this;if(e.fromParams=this.$route.query.params,"userAddrList"==e.fromParams){var o=P.getItem("selectAddr").toJson();o.defaultAddress=null!=o.defaultAddress&&o.defaultAddress,this.userAddr=o}else this.initDefaultAddr();if(e.params=P.getItem("qaStoreNo").toJson().qaStoreNo,console.log(e.params),this.shopCar=new u.a(this.$store),this.isMobile=new f,!this.shopCar.length())return console.log("isNUll"),this.$vux.toast.show({text:this.$t("closeAccount.pleaseSelGoods"),type:"text"}),setTimeout(function(){t.$router.openPage("/classification",{params:e.params})},1e3),!1;setTimeout(function(){t.showLoading=!1},2e4),this.initPayStyle(),this.queryPromotionByStoreNoNation(),this.initPayData()},methods:{open:function(t){this.$router.openPage(t)},initDefaultAddr:function(){var t=this,e=P.getItem("telUserNo").toJson();this.$http.get("/userInfo/findUserPostAddress?userNo="+e.userNo).then(function(e){if(console.log("/userInfo/findUserPostAddress?userNo="),console.log(e),e.data.data.length){var o=e.data.data;console.log(o),o.forEach(function(e,o){e.defaultAddress&&(e.defaultAddress=null!=e.defaultAddress&&e.defaultAddress,t.userAddr=e)})}}).catch(function(t){console.log(t)})},initPayStyle:function(){this.isPcMoWx="wx",this.listSel=this.listWx},initPayData:function(){var t=this;A=StripeCheckout.configure({key:"pk_live_3aqw1J17VC1gcSxl29khgL3u",image:t.sjtStripeLogo,locale:"auto",allowRememberMe:!0,token:function(e){console.log(e);var o={token:e.id,amount:100*t.allGoods.allGDOrderPrice,description:t.$t("closeAccount.milkyTea"),orderNum:t.orderNo};t.showLoading=!0,setTimeout(function(){t.showLoading=!1},2e4),t.$http.post("/stripe/charge",o).then(function(e){if(console.log(e),"en"==P.getItem("localLang").toString())var o=e.data.usErrorMsg;else o=e.data.cnErrorMsg;200==e.status&&("00000"==e.data.rspCode?(t.shopCar.removeAll(),t.showLoading=!1,t.$vux.toast.show({text:t.$t("closeAccount.paymentSuccess"),type:"text"}),t.$router.openPage("/mineOrder")):t.$vux.toast.show({text:o,type:"text"}))}).catch(function(e){console.log(e),t.showLoading=!1})}}),window.addEventListener("popstate",function(){A.close()})},stripePay:function(){A.open({name:"素匠·泰茶",description:this.$t("closeAccount.commodityInformation"),currency:"cad",amount:100*this.allGoods.allGDOrderPrice})},pcAliWxPayData:function(t,e){var o=this;console.log(t),console.log(e);var s=this;console.log("pcAliWxPayData");var a=s.$t("closeAccount.commodityInformation"),i={mchOrderNo:s.orderNo+"@"+x++,channelId:t,currency:"CAD",amount:100*s.allGoods.allGDOrderPrice,subject:"素匠·泰茶",body:a,extra:e};console.log(i),s.$http.post("/iotpay/charge",i).then(function(e){if(console.log(e),console.log("charge"),200==e.status&&"00000"==e.data.rspCode){console.log(JSON.parse(e.data.data));var a=JSON.parse(e.data.data);console.log(a.codeUrl),"ALIPAY_QR"==t?(o.pcPayUrl=a.payUrl,o.iotPayMsg="请打开手机支付宝扫一扫！"):"WX_NATIVE"==t&&(o.pcPayUrl="http://mobile.qq.com/qrcode?url="+a.codeUrl,o.iotPayMsg="请打开手机微信扫一扫！"),o.showHideOnBlur=!0}else s.$vux.toast.show({text:e.data.data.retMsg,type:"text"})}).catch(function(t){})},pcPayShow:function(){var t=this;console.log("pcPaySHow"),w=setInterval(function(){t.loopPcPaySuc()},1e4)},pcPayHide:function(){console.log("pcPayHide"),clearInterval(w),this.loopPcPaySuc()},loopPcPaySuc:function(){var t=this;t.$http.get("/userOrderInfo/selectbyorderno",{params:{orderNo:t.orderNo}}).then(function(e){if(console.log(e),"en"==P.getItem("localLang").toString())e.data.usErrorMsg;else e.data.cnErrorMsg;200==e.status&&"00000"==e.data.rspCode&&1==e.data.data.payStatus&&(t.shopCar.removeAll(),t.$vux.toast.show({text:t.$t("closeAccount.paymentSuccess"),type:"text"}),t.$router.openPage("/mineOrder"))}).catch(function(t){})},initYuyueTime:function(t,e){console.log("DB.getItem(isOrder).toString()");var o=t.slice(0,2),s=e.slice(0,2);console.log(P.getItem("isOrder").toString()),"isOrder"==P.getItem("isOrder").toString()?(this.isShowOrderTime=!0,this.checkerDemo=this.demo2):(this.isShowOrderTime=!1,this.checkerDemo=this.demo1);var a=[],i=[];console.log("theSecondStart");for(var n=o;n<s;n++){var r=n<10?"0"+n:n;a.push(r)}for(var l=0;l<60;l++){var c=l<10?"0"+l:l;i.push(c)}var d=(new Date).getHours()<10?"0"+(new Date).getHours():(new Date).getHours(),u=(new Date).getTime()-18e5,g=new Date(u).getMinutes()<10?"0"+new Date(u).getMinutes():new Date(u).getMinutes();if(-1==a.indexOf(d))this.formatDemoValue.push(this.$t("closeAccount.shopHasNot"),"closeDoor");else{var h=17==d?a.slice(a.indexOf(d)):a.slice(a.indexOf(d)+1);console.log(h),this.ppAllYuyueTime.push(h,i),this.formatDemoValue.push(h[0],i.slice(i.indexOf(g),59)[0])}},onHide:function(){console.log("关闭模态框")},onCancel:function(){console.log("取消模态框")},onConfirm5:function(t){console.log(t),this.storePhone=t},queryPromotionByStoreNoNation:function(){var t=this,e=this;this.$http.get("/userLogin/storelist").then(function(o){var s=o.data.data.data;console.log(s),console.log(e.params),console.log(s),s.forEach(function(o,s){o.storeNo==e.params&&(t.storeAddress=o.cnStoreAddress,t.storePhone=o.storePhone,t.gdTitle=o.cnStoreName)})}).catch(function(t){console.log(t)}),this.$http.get("/userLogin/queryPromotionByStoreNoNation",{params:{storeNo:P.getItem("storeNo").toString(),lang:P.getItem("localLang").toString()}}).then(function(e){console.log(e),200==e.status&&"00000"==e.data.rspCode&&(t.promotionId=e.data.data.data[0].promotionId,t.promotionName=e.data.data.data[0].promotionName)}).catch(function(t){console.log(t)}),this.initUserOrder()},initUserOrder:function(){var t=this;console.log("queryPromotionByStoreNoNation-telUserNo");var e=P.getItem("telUserNo").toJson(),o=this.shopCar.getAll();console.log(o);var s=[];for(var a in o){var i={};i.goodsId=o[a].goodsId,i.standardNum=o[a].length,i.standardId=JSON.parse(o[a].itemGuige[0].initGuiGeSC).id,s.push(i)}console.log("listTeaOrderDetails"),console.log(s);var n={userNo:e.userNo,telephone:e.telephone,promotionId:null,remark:this.textAreaValue,orderType:null,storeNo:this.params,listTeaOrderDetails:s};console.log("toPushData"),this.$http.post("/userOrderInfo/userOrderOper",n).then(function(e){if(console.log("/userOrderInfo/userOrderOper"),200==e.status){if("en"==P.getItem("localLang").toString())var o=e.data.usErrorMsg;else o=e.data.cnErrorMsg;"00000"==e.data.rspCode?(console.log(e.data.data),t.initOrderPage(e.data.data),t.orderNo=e.data.data.orderNo,t.goodsId=e.data.data.listTeaOrderDetails[0].goodsId,t.showInitData=!0):(t.$vux.toast.show({text:o,type:"text"}),t.$router.openPage("/classification"))}}).catch(function(t){console.log(t)})},initOrderPage:function(t){var e=this.shopCar.getAll(),o=0,s={allGDPage:[],allGDLength:t.listTeaOrderDetails.length,allGDOrigPrice:t.origPrice,allGDDiscount:t.discount,allGDOrderPrice:t.orderPrice};for(var a in e)e[a].itemGuige.forEach(function(t,i){var n={goodsItem:{}};n.goodsItem.goodsName=e[a].goodsItem.goodsName,n.goodsItem.goodsPrice=e[a].goodsItem.goodsPrice,n.goodsItem.goodsGuigePrice=e[a].goodsItem.goodsGuigePrice,n.goodsItem.goodsPictureRound=e[a].goodsItem.goodsPictureBig,n.goodsItem.goodsGuigePrice=t.guiGePrice,n.selectGoodsStyle=t.selectGoodsStyle,n.itemOneGuigeLen=t.itemOneGuigeLen,o+=t.itemOneGuigeLen,n.hasGuigePrice=t.hasGuigePrice,s.allGDPage.push(n)});s.allGDLength=o,console.log("allGoodsDataPage"),console.log(s),this.allGoods=s,this.showLoading=!1},finishPayModfiyOrder:function(){var t=this;if(this.isShowOrderTime)var e=y.ftNoHMS(new Date)+" "+this.formatDemoValue[0]+":"+this.formatDemoValue[1]+":00";else e="";console.log("theOrderTime"),console.log(e);var o={orderTime:e,orderNo:this.orderNo,remark:this.textAreaValue};this.$http.get("/userOrderInfo/finishPayModfiyOrder",{params:o}).then(function(t){console.log("/userOrderInfo/finishPayModfiyOrder"),console.log(t.data),200==t.status&&t.data.rspCode}).catch(function(t){console.log(t)});var s={orderNo:this.orderNo,postAddress:this.userAddr.accessAddress,postTel:this.userAddr.acceptTel,postName:this.userAddr.acceptName};this.userAddr.accessAddress?this.$http.post("/userOrderInfo/updateOrderSetPostInfo",s).then(function(e){console.log("/userOrderInfo/updateOrderSetPostInfo"),console.log(e.data),200==e.status&&"00000"==e.data.rspCode&&t.wxPay()}).catch(function(t){console.log(t)}):this.$vux.toast.show({text:"请选择收获地址！",type:"text"})},updateAccount:function(){this.allGoods.allGDOrderPrice&&this.finishPayModfiyOrder()},wxPay:function(){var t=this,e=P.getItem("telUserNo").toJson(),o={orderNO:this.orderNo,userId:e.userNo};this.$http.post("/wxpay/pay",o).then(function(e){console.log("/wxpay/pay"),console.log(e.data),200==e.status&&"00000"==e.data.rspCode&&e.data.data&&(console.log(e.data.data),(S=e.data.data).sign?(console.log("WeixinJSBridge-undefined"),console.log(WeixinJSBridge),"undefined"==typeof WeixinJSBridge?document.addEventListener?document.addEventListener("WeixinJSBridgeReady",O,!1):document.attachEvent&&(document.attachEvent("WeixinJSBridgeReady",O),document.attachEvent("onWeixinJSBridgeReady",O)):O()):t.$vux.toast.show({text:"微信支付调用失败！",type:"text"}))}).catch(function(t){console.log(t)})},nextWait:function(){this.showPositionValue=!0},changeChecker:function(t){console.log(t.key),0==t.key?(this.isShowOrderTime=!0,this.checkerDemo=this.demo2):(this.isShowOrderTime=!1,this.checkerDemo=this.demo1)}}},D={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("div",{staticClass:"app-init ca-cont"},[o("div",{staticClass:"classification-header"},[o("header-back",{attrs:{title:t.headTitle}})],1),t._v(" "),o("div",{staticClass:"absolute closeAccountCont"},[o("div",{staticClass:"groupCont"},[o("group",{staticClass:"groupItem",attrs:{"label-width":"4.5em","label-margin-right":"2em",gutter:"0","label-align":"left"}},[o("cell",{attrs:{"value-align":"left",primary:"content","is-link":""},nativeOn:{click:function(e){t.open("/userAddrList")}}},[o("div",{staticStyle:{width:"8rem"},attrs:{slot:"title"},slot:"title"},[o("div",{staticStyle:{"vertical-align":"middle",width:"8rem","font-size":"0.4rem"}},[t._v("\n\t\t\t\t\t\t\t \t\t"+t._s(t.userAddr.acceptName)),t.userAddr.defaultAddress?o("badge",{staticStyle:{"font-size":"0.2rem"},attrs:{text:"默认"}}):t._e(),t._v("　 "+t._s(t.userAddr.acceptTel)+"\n\t\t\t\t\t\t\t \t")],1),t._v(" "),o("div",{staticStyle:{"vertical-align":"middle",width:"8rem","font-size":"0.3rem"}},[t._v(t._s(t.userAddr.accessAddress))])])]),t._v(" "),o("popup-picker",{attrs:{title:t.modeOfPayment,data:t.listSel,"value-text-align":"left"},model:{value:t.valueDefault,callback:function(e){t.valueDefault=e},expression:"valueDefault"}})],1)],1),t._v(" "),o("div",{staticClass:"goodsDetail"},[o("div",{staticClass:"gd-title"},[t._v("\n\t\t\t\t"+t._s(t.gdTitle)+"\n\t\t\t")]),t._v(" "),t.showInitData?o("div",{staticClass:"gd-cont"},[t._l(t.allGoods.allGDPage,function(e,s){return o("div",{staticClass:"gdc-detail"},[o("x-img",{directives:[{name:"lazy",rawName:"v-lazy",value:e.goodsItem.goodsPictureRound,expression:"item.goodsItem.goodsPictureRound"}],staticClass:"gdcd-img"}),t._v(" "),o("div",{staticClass:"gdcd-price"},[o("div",{staticClass:"gdcdp-right"},[o("div",{staticClass:"gdcdpr-title"},[t._v(t._s(e.goodsItem.goodsName))]),t._v(" "),o("div",[t._v(t._s(e.selectGoodsStyle)+" "),e.goodsItem.goodsGuigePrice?o("span",{staticClass:"ft2"},[t._v("($"+t._s(e.goodsItem.goodsGuigePrice)+")")]):t._e()]),t._v(" "),o("div",[t._v("x"+t._s(e.itemOneGuigeLen))])]),t._v(" "),o("div",{staticClass:"gdcdp-plus"},[o("span",[t._v("$"+t._s(e.hasGuigePrice*e.itemOneGuigeLen))])])])],1)}),t._v(" "),o("div",{staticClass:"gdc-footer"},[o("div",{staticClass:"gdcf-allPrice"},[t._v("\n\t\t\t\t\t\t总价：\n\t\t\t\t\t\t"),o("span",{staticClass:"red"},[t._v("$"+t._s(t.allGoods.allGDOrigPrice))])]),t._v(" "),o("group",{staticClass:"gdc-textarea",attrs:{title:t.theSellerMessage}},[o("x-textarea",{attrs:{name:"detail",max:t.maxNum,placeholder:t.holdSay,"show-counter":!0},model:{value:t.textAreaValue,callback:function(e){t.textAreaValue=e},expression:"textAreaValue"}})],1)],1)],2):t._e()])]),t._v(" "),o("div",{staticClass:"classification-footer absolute"},[o("div",{staticClass:"cf-left"},[o("div",{staticClass:"cfl-cont"},[o("x-img",{directives:[{name:"lazy",rawName:"v-lazy",value:t.sjtLogo,expression:"sjtLogo"}],staticClass:"iconfont icon-gouwuche ftl-gwc"}),t._v(" "),t.allGoods.allGDLength?o("div",{staticClass:"ftl-redPoint"},[t._v("\n\t\t\t\t\t"+t._s(t.allGoods.allGDLength)+"\n\t\t\t\t")]):t._e()],1)]),t._v(" "),o("div",{staticClass:"cf-center"},[t._v("\n\t\t\t"+t._s(t.$t("classification.totalPrice"))+":$"+t._s(t.allGoods.allGDOrderPrice)+"\n\t\t")]),t._v(" "),o("div",{staticClass:"cf-right",on:{click:function(e){t.updateAccount()}}},[t._v("\n\t\t\t"+t._s(t.$t("closeAccount.submitOrder"))+"\n\t\t")])]),t._v(" "),o("div",[o("confirm",{ref:"confirm5",attrs:{"show-input":"",title:t.showPhoneTitle},on:{"on-cancel":t.onCancel,"on-confirm":t.onConfirm5,"on-hide":t.onHide},model:{value:t.showPhone,callback:function(e){t.showPhone=e},expression:"showPhone"}})],1),t._v(" "),o("loading",{attrs:{show:t.showLoading,"is-show-mask":"",text:t.showText}}),t._v(" "),o("toast",{attrs:{type:"text",time:2e3,"is-show-mask":"",text:t.enjoyLooking,position:t.position},model:{value:t.showPositionValue,callback:function(e){t.showPositionValue=e},expression:"showPositionValue"}}),t._v(" "),o("div",{staticClass:"dialogGoodsDet"},[o("x-dialog",{staticClass:"dialog-demo",attrs:{"hide-on-blur":"","dialog-style":{width:"60%","max-width":"6rem",overflow:"visible"}},on:{"on-show":t.pcPayShow,"on-hide":t.pcPayHide},model:{value:t.showHideOnBlur,callback:function(e){t.showHideOnBlur=e},expression:"showHideOnBlur"}},[o("div",{staticClass:"img-box"},[o("div",{staticClass:"dgdCont"},[t._v("\n\t          \t"+t._s(t.iotPayMsg)+"\n\t          ")]),t._v(" "),o("img",{staticStyle:{"max-width":"6rem"},attrs:{src:t.pcPayUrl}})])])],1)],1)},staticRenderFns:[]};var _=o("VU/8")(I,D,!1,function(t){o("xUx8")},"data-v-62f7dba5",null);e.default=_.exports},xUx8:function(t,e){}});
//# sourceMappingURL=9.bfa5c8416953605e4d25.js.map