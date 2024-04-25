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
                  <el-menu-item @click="userManage" index="1-1">用户管理</el-menu-item>
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
            <el-row>
              <!-- 左侧三分之一，头像展示 -->
              <el-col :span="8">
                <div class="avatar-section">
                    <el-avatar shape="square" :size="150" :fit='pic' :src="url"></el-avatar>
                  <div>我的头像</div>
                </div>
              </el-col>
              <!-- 右侧三分之二，日历展示 -->
              <el-col :span="16">
                <div class="calendar-section">
                  <el-calendar v-model="date">
                  </el-calendar>
                  <h3>Calendar</h3>
                </div>
              </el-col>
            </el-row>
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
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        date: new Date(),
        headerColor: '#1E88E5',
        mainColor: '#BBDEFB'
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      userManage(){
        this.$router.push('/usermanage');
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
  
  .avatar-section {
    height: 100%;
    padding: 20px;
    margin-top: 50%;
  }
  
  .calendar-section {
    height: 100%;
    padding: 20px;
  }
  
  /* 媒体查询 */
  @media screen and (max-width: 800px) {
    .aside {
      display: none; /* 当屏幕宽度小于800px时，隐藏aside */
    }
  }
  </style>
  