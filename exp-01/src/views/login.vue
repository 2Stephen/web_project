<template>
    <div id="background">
        <div id="login">
            <el-card>
               <div id="login-text">登录</div>
               <div id="form">
                   <div class="box1">
                       <el-button class="btn" @click="qqLogin" type="primary" plain>QQ登录</el-button>
                       <el-button class="btn" @click="wechatLogin" type="success" plain>微信登录</el-button>
                       <el-button class="btn" @click="aliLogin" type="info" plain>支付宝登录</el-button>
                   </div>
                   <el-divider direction="vertical"></el-divider>
                   <div class="box2">
                       <el-input class="input-area" v-model="inputName" placeholder="请输入用户名"></el-input>
                       <el-input class="input-area" v-model="inputPwd" placeholder="请输入密码" show-password></el-input>
                       <el-button class="input-area" @click="validate" type="primary">登录</el-button>
                   </div>
               </div>
               <div id="bot-text">
                <router-link to="/register" class="bot-link" target="blank">注册账号</router-link>
                <router-link to="/changepwd" class="bot-link" target="blank">修改密码</router-link>
                <router-link to="/forget" class="bot-link" target="blank">忘记密码</router-link>
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
#login{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}
#login-text{
    font-size: large;
}
#form{
    display: flex;
    justify-content: space-between; /* 在两个组件之间创建等间距的空间 */ 
}
#bot-text{
    display: flex;
    justify-content: space-between; /* 在两个组件之间创建等间距的空间 */ 
    margin-bottom: .625rem;
}
.el-card{
    width: 31.25rem;
    height: 18.75rem;
    background-color: rgba(255, 255, 255, 0.4);
}
.el-divider--vertical {
    display: inline-block;
    width: 1px;
    height: 7.5rem;
    margin-top: 1.875rem;
    vertical-align: middle;
    position: relative;
}
.box1, .box2 {
    width: 40%;
    display: flex;
    flex-direction: column;
}
.btn{
    margin-top: .625rem;
    margin-bottom: .625rem;
    margin-left: 0.625rem;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}
.input-area{
    margin-top: .625rem;
    margin-bottom: .625rem;

    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
}
.bot-link{
    width: 40%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: .625rem;
}
/* 在窄屏时，修改布局为竖直放置 */
@media screen and (max-width: 50rem) {
    .box1 {
        width: 100%;
    }
    .box2 {
        width: 100%;
        margin-top: 20%;
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
    .el-divider--vertical {
        display: none;
    }
}

</style>
<script>
import axios from 'axios';
    export default {
        data() {
            return {
            inputName: '', // 用户名
            inputPwd: '',   // 密码
            isVerified: false
            }
        },
        methods: {
            validate() {
                // 后端验证
                var login_data = {
                    username: this.inputName,
                    password: this.inputPwd
                };
                if (this.inputName.length === 0 || this.inputPwd.length === 0) {
                    this.$message.error("用户名或密码不能为空！");
                    return;
                }
                axios.post('http://localhost:8081/api/login',login_data)
                    .then((response) => {
                        // 请求成功处理
                        this.fetchUsers(login_data);
                        this.$router.push('/home');
                    })
                    .catch((error) => {
                        // 请求失败处理
                        console.error(error);
                        this.$message.error("用户名或密码错误！");
                    });
                
            },
            async fetchUsers(login_data) {
                axios.post('http://localhost:8081/api/userinfo',login_data)
                    .then((response) => {
                        // 请求成功处理
                        console.log(response.data);
                        const isoString = response.data.birthday;
                        const date = new Date(isoString);

                        const year = date.getUTCFullYear();
                        const month = String(date.getUTCMonth() + 1).padStart(2, '0');
                        const day = String(date.getUTCDate()).padStart(2, '0');

                        const formattedDate = `${year}-${month}-${day}`;

                        this.updateUsername(response.data.username);
                        this.updateEmail(response.data.email);
                        this.updateBirthday(formattedDate);
                        this.updateBalance(response.data.balance);
                        this.updateAvatar(response.data.avatar);
                        console.log(response.data.avatar);
                    })
                    .catch((error) => {
                        // 请求失败处理
                        console.error(error);
                        this.$message.error("获取用户信息失败！");
                    });
            },
            updateUsername(newUsername) {
            this.$store.dispatch('updateUsername', newUsername); // 更新用户名
            },
            updateEmail(newEmail) {
            this.$store.dispatch('updateEmail', newEmail); // 更新邮箱
            },
            updateBirthday(newBirthday) {
            this.$store.dispatch('updateBirthday', newBirthday); // 更新生日
            },
            updateBalance(newBalance) {
            this.$store.dispatch('updateBalance', newBalance); // 更新余额
            },
            updateAvatar(newAvatar) {
            this.$store.dispatch('updateAvatar', newAvatar); // 更新头像
            },
            qqLogin(){
                this.$router.push('/QQLogin');
            },
            wechatLogin(){
                this.$router.push('/WechatLogin');
            },
            aliLogin(){
                this.$router.push('/AliLogin');
            }
    }
}
  
</script>