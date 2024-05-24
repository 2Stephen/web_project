<template>
  <div class="main-page">
    <el-container class="container">
      <!-- Header -->
      <el-header class="header" :style="{backgroundColor:headerColor}">
        <div id="menu">
          <el-dropdown trigger="click" @command="handleClick">
            <span class="el-dropdown-link">
              下拉菜单<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="calendar">日历</el-dropdown-item>
              <el-dropdown-item command="info">个人信息</el-dropdown-item>
              <el-dropdown-item command="exit">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
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
                <el-menu-item @click="userManage" index="1-1">作者管理</el-menu-item>
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
                <el-avatar shape="square" :size="150" :src="avatar"></el-avatar>
                <div>我的头像</div>
              </div>
            </el-col>
            <!-- 右侧三分之二，展示 -->
            <el-col :span="16">
              <div class="calendar-section" v-if="isShowCalendar">
                <h1>日历</h1>
                <el-calendar v-model="date"></el-calendar>
                <h3>Calendar</h3>
              </div>
              <div>
                <el-card v-if="isShowInfo" class="customCard">
                  <div slot="header" class="clearfix">
                    <span>个人信息</span>
                  </div>
                  <div>
                    <el-row>
                      <el-col id="label">
                        <el-form label-position="left" label-width="5rem">
                          <el-form-item label="用户名">
                            <el-input v-model="username" readonly class="inputRow"></el-input>
                          </el-form-item>
                          <el-form-item label="邮箱">
                            <el-input v-model="email" readonly class="inputRow"></el-input>
                          </el-form-item>
                          <el-form-item label="余额">
                            <el-input v-model="balance" readonly class="inputRow"></el-input>
                          </el-form-item>
                          <el-form-item label="生日">
                            <el-input v-model="birthday" readonly class="inputRow"></el-input>
                          </el-form-item>
                        </el-form>
                      </el-col>
                    </el-row>
                  </div>
                </el-card>
              </div>              
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  name: 'MainPage',
  computed: {
    ...mapGetters(['username', 'email', 'birthday', 'balance', 'avatar']),
  },
  data() {
    return {
      date: new Date(),
      headerColor: '#1E88E5',
      mainColor: '#BBDEFB',
      isShowCalendar: false,
      isShowInfo: true
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
      this.headerColor='#b2b528';
      this.mainColor='#fff957';
    },
    bluePanel(){
      this.headerColor='#1E88E5';
      this.mainColor='#BBDEFB';
    },
    handleClick(command) {
      if (command === 'calendar') {
        this.isShowCalendar = true;
        this.isShowInfo = false;
      } else if (command === 'info') {
        this.isShowInfo = true;
        this.isShowCalendar = false;
      } else if (command === 'exit') {
        this.$router.push('/');
      }
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
  .clearfix::after {
  content: "";
  display: block;
  clear: both;
  display: grid;
    place-items: center; /* 水平和垂直居中 */
  }
  #customCard {

    display: grid;
    place-items: center; /* 水平和垂直居中 */
  }
  .el-col-16 {
    display: grid;
    place-items: center; /* 水平和垂直居中 */
    height: 75vh; /* 高度为视口高度 */
}
  #menu {
    float: right;
    margin-right: 1.25rem;
    margin-top: 1.25rem;
  }
  .container {
    height: 100%;
  }
  #label {
    display: grid;
    place-items: center; /* 水平和垂直居中 */
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
  .el-form-item__content {
    line-height: 40px;
    position: relative;
    font-size: 14px;
    margin-right: -12.5rem;
  }
  .calendar-section {
    height: 100%;
    padding: 20px;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #000;
  }
  .el-icon-arrow-down {
    font-size: 12px;;
  }
  .narrow-input {
  max-width: 100px; /* 设置输入框的最大宽度 */
  }
  
  /* 媒体查询 */
  @media screen and (max-width: 800px) {
    .aside {
      display: none; /* 当屏幕宽度小于800px时，隐藏aside */
    }
  }
  </style>
  