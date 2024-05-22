<template>
  <div class="main-page">
    <el-container class="container">
      <!-- Header -->
      <el-header class="header" :style="{ backgroundColor: headerColor }">
        <h1>作者管理</h1>
      </el-header>

      <el-container class="content-container">
        <!-- Aside -->
        <el-aside class="aside">
          <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>功能菜单</span>
              </template>
              <el-menu-item-group>
                <el-menu-item @click="pushHome" index="1-1">我的主页</el-menu-item>
                <el-menu-item @click="docManage" index="1-2">文章管理</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-menu"></i>
                <span>主题颜色</span>
              </template>
              <el-menu-item-group>
                <el-menu-item @click="yellowPanel" index="2-1">黄色风格</el-menu-item>
                <el-menu-item @click="bluePanel" index="2-2">蓝色风格</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            <el-menu-item index="3" disabled>
              <i class="el-icon-document"></i>
              <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">导航四</span>
            </el-menu-item>
          </el-menu>
        </el-aside>

        <!-- Main -->
        <el-main class="main" :style="{ backgroundColor: mainColor }">
          <div class="upfield">
            <el-button type="primary" icon="el-icon-plus" @click="adduser">新增</el-button>
            <el-input v-model="inputUserName" placeholder="请输入姓名" suffix-icon="el-icon-search" class="right-input"></el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchuser">查询</el-button>
          </div>
          <el-table
            :data="tableData"
            :border="true"
            style="width: 100%">
            <el-table-column
              fixed
              prop="date"
              label="日期"
              width="150">
            </el-table-column>
            <el-table-column
              prop="name"
              label="姓名"
              width="120">
            </el-table-column>
            <el-table-column
              prop="province"
              label="省份"
              width="120">
            </el-table-column>
            <el-table-column
              prop="city"
              label="市区"
              width="120">
            </el-table-column>
            <el-table-column
              prop="address"
              label="地址"
              width="300">
            </el-table-column>
            <el-table-column
              prop="zip"
              label="邮编"
              width="120">
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                <el-button type="text" size="small">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
          </el-pagination>
        </el-main>
      </el-container>
    </el-container>

    <!-- Form Dialog -->
    <el-dialog :visible.sync="isadduser" title="新增作者" :modal="false" class="form">
      <el-form :model="form" label-position="left" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="form.name" placeholder="姓名" class="full-width"></el-input>
        </el-form-item>
        <!-- 二级联动 -->
        <el-form-item label="日期">
          <el-date-picker
            v-model="form.date"
            type="date"
            placeholder="选择日期"
            class="full-width">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="省份">
          <el-select v-model="form.province" class="full-width" placeholder="请选择">
          <el-option
            v-for="item in provinces"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="市区">
          <el-select v-model="form.city" class="full-width" placeholder="请选择">
          <el-option
            v-for="item in cities"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" placeholder="地址" class="full-width"></el-input>
        </el-form-item>
        <el-form-item label="邮编">
          <el-input v-model="form.zip" placeholder="邮编" class="full-width"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="quitForm">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

            
  <script>
  export default {
    name: 'MainPage',
    data() {
      return {
        provinces: [
        { value: 'beijing', label: '北京市' },
        { value: 'tianjin', label: '天津市' },
        { value: 'hebei', label: '河北省' },
        { value: 'shanxi', label: '山西省' },
        { value: 'neimenggu', label: '内蒙古自治区' },
        { value: 'liaoning', label: '辽宁省' },
        { value: 'jilin', label: '吉林省' },
        { value: 'heilongjiang', label: '黑龙江省' },
        { value: 'shanghai', label: '上海市' },
        { value: 'jiangsu', label: '江苏省' },
        { value: 'zhejiang', label: '浙江省' },
        { value: 'anhui', label: '安徽省' },
        { value: 'fujian', label: '福建省' },
        { value: 'jiangxi', label: '江西省' },
        { value: 'shandong', label: '山东省' },
        { value: 'henan', label: '河南省' },
        { value: 'hubei', label: '湖北省' },
        { value: 'hunan', label: '湖南省' },
        { value: 'guangdong', label: '广东省' },
        { value: 'guangxi', label: '广西壮族自治区' },
        { value: 'hainan', label: '海南省' },
        { value: 'chongqing', label: '重庆市' },
        { value: 'sichuan', label: '四川省' },
        { value: 'guizhou', label: '贵州省' },
        { value: 'yunnan', label: '云南省' },
        { value: 'xizang', label: '西藏自治区' },
        { value: 'shanxi2', label: '陕西省' },
        { value: 'gansu', label: '甘肃省' },
        { value: 'qinghai', label: '青海省' },
        { value: 'ningxia', label: '宁夏回族自治区' },
        { value: 'xinjiang', label: '新疆维吾尔自治区' },
        { value: 'taiwan', label: '台湾省' },
        { value: 'xianggang', label: '香港特别行政区' },
        { value: 'aomen', label: '澳门特别行政区' }
      ],
        form: {
          name: '',
          date: '',
          province: '',
          city: '',
          address: '',
          zip: ''
        },
        inputUserName: '',
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333
        }, {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1517 弄',
          zip: 200333
        }, {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1519 弄',
          zip: 200333
        }, {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        }],
        headerColor: '#1E88E5',
        mainColor: '#BBDEFB',
        isadduser: false,
      }

    },
    methods:{
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClick(row) {
        console.log(row);
      },
      pushHome(){
        this.$router.push('/home');
      },
      docManage(){
        this.$router.push('/docmanage');
      },
      yellowPanel(){
        this.headerColor='#b2b528'
        this.mainColor='#fff957'
      },
      bluePanel(){
        this.headerColor='#1E88E5'
        this.mainColor='#BBDEFB'
      },
      adduser(){
        console.log('adduser');
        this.isadduser = true;
      },
      searchuser(){
        console.log('searchuser');
      },
      quitForm(){
        this.form = {
          name: '',
          date: '',
          province: '',
          city: '',
          address: '',
          zip: ''
        };
        this.isadduser = false;
      },
      submitForm(){
        this.isadduser = false;
        axios.post('http://localhost:8081/api/addauthor',this.form)
          .then((response) => {
            // 请求成功处理
            console.log(response.data);
            this.form = {
              name: '',
              date: '',
              province: '',
              city: '',
              address: '',
              zip: ''
            };
            this.$message.success('新增成功');
          })
          .catch((error) => {
            // 请求失败处理
            console.error(error);
          });
      },
    }
  }
  </script>

  <style scoped>
  .main-page {
    position: fixed;
    height: 100%;
    width: 100%;
    top: 0%;
    left: 0%;
    height: 100vh;
  }
  .upfield {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
  }
  .right-input {
    width: 250px;
    margin-left: auto;
    margin-right: 10px;
  }
  .form {
    height: 90%;
  }
  .container {
    height: 100%;
  }
  
  .content-container {
    height: 100%;
  }
  
  .header {
    background-color: #1E88E5; /* 深蓝色 */
    color: #fff;
  }
  
  .aside {
    background-color: #fff; /* 白色 */
    height: 100%;
  }
  
  .main {
    background-color: #BBDEFB; /* 浅蓝色 */
    height: 100%;
  }
  .full-width {
    width: 100%;
  }
  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
  }
  
  
  /* 媒体查询 */
  @media screen and (max-width: 800px) {
    .aside {
      display: none; /* 当屏幕宽度小于800px时，隐藏aside */
    }
  }
  </style>
  