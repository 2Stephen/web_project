<template>
    <div id="background">
      <div id="login">
        <el-card class="box-card">
          <div id="login-text">注册账号</div>
          <div id="form">
            <div class="box1">
              <el-input class="input-area" v-model="inputName" placeholder="请输入用户名"></el-input>
              <el-input class="input-area" v-model="inputPwd" placeholder="请输入密码" show-password></el-input>
              <el-input class="input-area" v-model="confirmPwd" placeholder="请确认密码" show-password></el-input>
              <el-input class="input-area" v-model="email" placeholder="您的邮箱：example@qq.com"></el-input>
              <el-date-picker
                class="input-area"
                v-model="date"
                type="date"
                placeholder="年/月/日"
                :picker-options="pickerOptions">
            </el-date-picker>
            </div>
            <div class="box2">
              <el-upload
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="true"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
              <div v-if="!imageUrl" class="error-message">请上传头像图片</div>
            </div>
          </div>
          <div>
            <el-button class="input-area" @click="register" type="primary">注册</el-button>
          </div>
          <div id="bot-text">
            <router-link to="/" class="bot-link">已有账号，去登录</router-link>
            <router-link to="/forget" class="bot-link">忘记密码</router-link>
          </div>
        </el-card>
      </div>
    </div>
  </template>
<script>
export default {
  data() {
    return {
      inputName: '',
      inputPwd: '',
      confirmPwd: '',
      imageUrl: '',
      date: '',
      email: ''
    };
  },
  methods: {
    handleAvatarSuccess(response, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || ile.type === 'image/jpg'; // Allow both JPEG and PNG formats
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式！');
      }
      return isJPG;
    },
    register() {
      // 后端验证
      var register_data = {
          username: this.inputName,
          password: this.inputPwd,
          email: this.email,
          birthday: this.date
      };
      if (!this.inputName || !this.inputPwd || !this.confirmPwd || !this.date || !this.email) {
        this.$message.error('请输入完整的用户名、密码、生日、邮箱并上传头像！');
        return;
      }  
      const reg = /^([a-zA-Z]|[0-9])(\w|\-|\.)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/
      if (!reg.test(this.email)) {
        this.$message.error('邮箱格式不正确！');
        return;
      } 
      if (this.inputPwd.length < 6) {
        this.$message.error('密码长度不能少于6位！');
        return;
      }
      if (this.inputPwd !== this.confirmPwd) {
        this.$message.error('两次输入的密码不一致！');
        return;
      }
      axios.post('http://localhost:8081/api/register',register_data)
          .then((response) => {
              // 请求成功处理
              console.log(response);
              this.$message.success('注册成功！');
              this.$router.push('/');
          })
          .catch((error) => {
              // 请求失败处理
              console.error(error);
              this.$message.error("用户名已存在！");
              return;
          });
    }
  }
};
</script>

<style scoped>
  #background {
    position: fixed;
    height: 100%;
    width: 100%;
    top: 0%;
    left: 0%;
    background-image: linear-gradient(90deg, cyan, purple);
    background-size: 400%;
    animation: myanimation 10s infinite;
  }
  
  @keyframes myanimation {
    0% {
      background-position: 0% 50%;
    }
    50% {
      background-position: 100% 50%;
    }
    100% {
      background-position: 0% 50%;
    }
  }
  
  #login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  
  #login-text {
    font-size: large;
  }
  
  #form {
    display: flex;
    justify-content: space-between;
  }
  
  #bot-text {
    display: flex;
    justify-content: space-between;
    margin-bottom: 0.625rem;
  }
  
  .el-card {
    width: 31.25rem;
    height: 25rem;
    background-color: rgba(255, 255, 255, 0.4);
  }
  
  .box1{
    display: flex;
    flex-direction: column;
  }
  .box2 {
    display: flex;
    flex-direction: column;
    border: 1px dashed #000000;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #000000;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  
  .btn {
    margin-top: 0.625rem;
    margin-bottom: 0.625rem;
    margin-left: 0.625rem;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }
   /*重载el-date-picker的默认宽度*/
  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
}
  .input-area {
    margin-top: 0.625rem;
    margin-bottom: 0.625rem;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
  }

  .bot-link {
    width: 40%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 0.625rem;
  }
  
  /* 在窄屏时，修改布局为竖直放置 */
  @media screen and (max-width: 50rem) {
    /*重载el-date-picker的默认宽度*/
    .el-date-editor.el-input, .el-date-editor.el-input__inner {   
      width: 90%;
    }
    .box1{
      width: 100%;
      display: flex;
      align-items: center;
      flex-direction: column;
    }
    .box2 {
      width: 100%;
      align-items: center;
      display: flex;
      flex-direction: column;
      border: 1px dashed #000000;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
  }
    #form {
      flex-direction: column;
    }
    .btn {
      margin-top: 0.625rem;
      margin-left: 0.625rem;
      display: flex;
      justify-content: center;
      align-items: center;
      width: 90%;
    }
    .input-area {
      margin-top: 0.625rem;
      margin-left: 0.625rem;
      display: flex;
      justify-content: center;
      align-items: center;
      width: 90%;
    }
    .el-card {
      width: 80%;
      height: 90%;
    }
  }
  .error-message {
    color: red;
    margin-top: 5px;
  }
  </style>
  