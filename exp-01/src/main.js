import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import AMapLoader from '@amap/amap-jsapi-loader'

Vue.config.productionTip = false

AMapLoader.load({
  key: '2a1c9ab319ba9bc0d4bd9dc882b7cf85',
  version: '2.0',
  plugins: ['AMap.Geocoder']
}).then((AMap) => {
  Vue.prototype.$AMap = AMap;

  new Vue({
    router,
    store,
    render: function (h) { return h(App) }
  }).$mount('#app')

}).catch(e => {
  console.error(e);
});
