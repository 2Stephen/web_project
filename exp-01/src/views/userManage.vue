<template>
    <div class="main-page">
      <el-container class="container">
        <!-- Header -->
        <el-header class="header" :style="{backgroundColor:headerColor}">
          <h1>Header</h1>
        </el-header>
        
        <el-container class="content-container">
          <!-- Aside -->
          <el-aside class="aside" >
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
          <el-main class="main" :style="{backgroundColor:mainColor}">
            <template>
            <el-table
                :data="tableData"
                :border=true
                stripe=true
                style="width: 100%">
                <el-table-column
                prop="date"
                label="日期"
                width="180">
                </el-table-column>
                <el-table-column
                prop="name"
                label="姓名"
                width="180">
                </el-table-column>
                <el-table-column
                prop="address"
                label="地址">
                </el-table-column>
            </el-table>
            </template>

                    </el-main>
                    </el-container>
                </el-container>
                </div>
            </template>
            
  <script>
  export default {
    name: 'MainPage',
    data() {
      return {
        headerColor: '#1E88E5',
        mainColor: '#BBDEFB',
        tableData: [{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1517 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1519 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }, {
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1516 弄'
        }]
      }

    },
    methods:{
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
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
      }
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
  
  
  /* 媒体查询 */
  @media screen and (max-width: 800px) {
    .aside {
      display: none; /* 当屏幕宽度小于800px时，隐藏aside */
    }
  }
  </style>
  