<template>
  <div class="login-container">
    <el-form :model="ruleForm2" :rules="rules2"
             status-icon
             ref="ruleForm2"
             label-position="left"
             label-width="0px"
             class="login-page">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text"
                  v-model="ruleForm2.username"
                  auto-complete="off"
                  placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password"
                  v-model="ruleForm2.password"
                  auto-complete="off"
                  placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="handleSubmit" >登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
 
<script>
import url from 'postcss-url';

  export default {
    name: "login",
    data(){
      return {
        url: 'D:\workspace-Java\Fair\vuePage\static\images\miku01.jpg',
        logining: false,
        ruleForm2: {
          username: '',
          password: '',
        },
        rules2: {
          username: [{required: true, message: 'please enter your account', trigger: 'blur'}],
          password: [{required: true, message: 'enter your password', trigger: 'blur'}]
        },
      };
    },
    methods: {
      handleSubmit(ruleForm2) {
        var param = {adminName: this.ruleForm2.username, adminPwd: this.ruleForm2.password}
        this.$axios({
          method: 'post',
          url: 'http://localhost:9090/login',
          data: param
        }).then(response=>{
            if (response.data.flag==true)
            {
                this.$message({
                    message: response.data.message,
                    type: 'success'
                });
                setTimeout("window.location.href='http://localhost:8080/#/center'",1000)
            }else {
                this.$message.error(response.data.message);
            }
            })
      }
    }
  }
</script>
 
<style scoped>
  .login-container {
    width: 100%;
    height: 100%;
    background: url('../assets/miku01.jpg');
    
  }
  .login-page {
    -webkit-border-radius: 5px;
    border-radius: 5px;
    width: 350px;
    padding: 35px 35px 15px;
    background: #fff;
    border: 1px solid #eaeaea;
    margin: 0;
    position: relative;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);

    box-shadow: 0px 5px 20px black;
    background-color: rgba(255, 255, 255, 0.3);
    color: #5c5c5c;
    z-index: 9;/*makes the nav always on the top*/
    backdrop-filter: blur(10px);/*add blur filter*/
  }
  .title{
    text-align: center;
    color: black;
  }
</style>
 