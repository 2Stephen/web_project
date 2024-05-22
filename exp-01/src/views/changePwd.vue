<template>
    <div id="background">
        <div id="changepwd">
            <el-card class="box-card">
               <div id="changepwd-text">修改密码</div>
               <div id="form">
                   <div class="box1">
                       <el-input class="input-area" v-model="inputName" placeholder="请输入用户名"></el-input>
                       <el-input class="input-area" v-model="inputPwd" placeholder="请输入原密码" show-password></el-input>
                   </div>
                   <div class="box2">
                       <el-input class="input-area" v-model="newPassword" placeholder="请输入新密码" show-password></el-input>
                       <el-input class="input-area" v-model="confirmPwd" placeholder="请确认新密码" show-password></el-input>
                   </div>
               </div>
               <div id="form">
                   <div class="box3">
                       <el-button @click="changepwd" class="btn" type="primary" plain>修改密码</el-button>
                   </div>
                   <div class="box4">
                       <el-button @click="returnlogin" class="btn" type="info" plain>返回登录</el-button>
                   </div>
               </div>
            </el-card>
        </div>
    </div>
</template>

<style scoped>
#background{
    position: fixed;
    height: 100%;
    width: 100%;
    top: 0%;
    left: 0%;
    background-image: linear-gradient(
        90deg,
        cyan,
        purple
    );
    background-size: 400%;
    animation: myanimation 10s infinite;
}
@keyframes myanimation{
    0%{
        background-position: 0% 50%;
    }
    50%{
        background-position: 100% 50%;
    }
    100%{
        background-position: 0% 50%;
    }
}
#changepwd{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
#changepwd-text{
    font-size: large;
}
#form{
    display: flex;
    justify-content: space-between; /* 在两个组件之间创建等间距的空间 */ 
}
.el-card{
    width: 31.25rem;
    height: 18.75rem;
    background-color: rgba(255, 255, 255, 0.4);
}
.box1, .box2 {
    width: 40%;
    display: flex;
    flex-direction: column;
}
.box3,.box4{
    width: 40%;
    display: flex;
    flex-direction: column;
}
.input-area{
    margin-top: .625rem;
    margin-bottom: .625rem;

    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}
.btn{
    margin-top: .625rem;
    margin-bottom: .625rem;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}
/* 在窄屏时，修改布局为竖直放置 */
@media screen and (max-width: 50rem) {
    .box1,.box4 {
        width: 100%;
    }
    .box2 ,.box3{
        width: 100%;
        margin-top: 10%;
    }
    #form {
        flex-direction: column;
    }
    .btn{
        margin-top: .625rem;
        margin-left: 0.625rem;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 90%;
    }
    .input-area{
        margin-top: .625rem;
        margin-left: 0.625rem;
        margin-bottom: .625rem;

        display: flex;
        justify-content: center;
        align-items: center;
        width: 90%;
    }
    .el-card {
        width: 80%;
        height: 80%;
    }
}
</style>
<script>
export default {
  data() {
    return {
      inputName: '', // 用户名
      inputPwd: '',   // 原密码
      newPassword: '',     // 新密码
      confirmPwd: '', // 确认新密码
    }
  },
  methods: {
    changepwd() {
        var changepwd_data = {
            username: this.inputName,
            password: this.inputPwd,
            newPassword: this.newPassword
        }
      if (!this.inputName || !this.inputPwd || !this.confirmPwd || !this.newPassword ) {
        this.$message.error('请输入完整的用户名、密码以及更改后的密码！');
        return;
      }
      if(this.newPassword.length < 6) {
        this.$message.error('密码长度不能少于6位！');
        return;
      }
      if (this.newPassword !== this.confirmPwd) {
        this.$message.error('两次密码不一致！');
        return;
      }
      if (this.newPassword === this.inputPwd) {
        this.$message.error('新密码不能和原密码一致！');
        return;
      }
      console.log(changepwd_data);
      axios.post('http://localhost:8081/api/changePassword',changepwd_data)
                    .then((response) => {
                        // 请求成功处理
                        console.log(response);
                        this.$message.success('修改成功！');
                        this.$router.push('/');
                    })
                    .catch((error) => {
                        // 请求失败处理
                        console.error(error);
                        this.$message.error("用户名或原密码错误！");
                    });
            
    },
    returnlogin() {
      this.$router.push("/");
    }
  }
}

</script>