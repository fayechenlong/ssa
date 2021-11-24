<template>
  <div class="home-page">
    <section
        class="top-section"
        :style="{
        background:'url('+bgImg+') repeat',
        backgroundSize: '14px 14px',
    }"
    >
    <div class="vertical-middle product-area">
      <img class="product-logo" src="../assets/icon.png" />
      <p class="product-desc">
       just for job !
      </p>
    </div>
    <div class="animation animation1" />
    <div class="animation animation2" />
    <div class="animation animation3" />
    <div class="animation animation4" />
    <div class="animation animation5" />
    <el-card class="login-panel" contentHeight="auto">
      <div class="login-header">登陆</div>
      <div class="internal-sys-tip">

      </div>
      <el-form
          class="login-form"
          ref="loginForm"
          label-position="top"
          :model="formLogin"
          size="default">
        <el-form-item prop="username">
          <el-input
              type="text"
              v-model="formLogin.username"
              placeholder="用户名">
            <i slot="prepend" class="fa fa-user-circle-o"></i>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
              type="password"
              v-model="formLogin.password"
              placeholder="密码">
            <i slot="prepend" class="fa fa-keyboard-o"></i>
          </el-input>
        </el-form-item>
        <div class="form-bottom">
          <el-button
              size="default"
              @click="submit"
              type="primary"
              class="button-login">
            登录
          </el-button>
        </div>
      </el-form>
    </el-card>
    </section>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "login",
  data() {
    return {
      formLogin: {username: 'admin', password: 'admin123'},
      bgImg :require('../assets/black_dot.png'),
  }
  },
  methods: {
    ...mapActions(['login']),
    submit() {
      this.login(this.formLogin).then(() => {
        this.$router.push('/foo')
      })
    }
  }
}
</script>

<style lang="less" scoped>

@animationDuration: 2s;

// 品牌色
@brandColor: #2e3034;
@mobileWidth: 640px;
// 页面主体最大宽度
@contentWidth: 1280px;

@keyframes slashStar {
  0% {
    opacity: 1;
  }

  100% {
    opacity: 0;
  }
}

.home-page {
  .top-section {
    position: relative;
    height: 100vh;
    .login-panel {
      position: absolute;
      right: 40px;
      width: 480px;
      height: 540px;
      top: 90px;
      border: 0px;
      input,
      input::-webkit-input-placeholder {
        font-size: 16px;
      }
      .login-header {
        width: 100%;
        line-height: 45px;
        font-size: 32px;
        margin-top: 58px;
        text-align: center;
      }
      .internal-sys-tip {
        width: 100%;
        line-height: 25px;
        font-size: 20px;
        margin-top: 25px;
        text-align: center;
        font-weight: 800;
        color: #ff0000cc;
      }
      .login-form {
        width: 360px;
        margin: 40px auto auto auto;
        input {
          height: 60px;
        }
        button {
          width: 100%;
          height: 60px;
          font-size: 16px;
          background: #4190ff 100%;
          color: white;
          border: 0px;
        }
      }
    }
    .animation {
      position: absolute;
      width: 6px;
      height: 6px;
      border-radius: 50%;
      background-color: #1be1f6;
      &1 {
      left: 15%;
      top: 70%;
      animation: slashStar @animationDuration ease-in-out 0.3s infinite;
    }
    &2 {
      left: 34%;
      top: 35%;
      animation: slashStar @animationDuration ease-in-out 1.2s infinite;
    }
    &3 {
      left: 53%;
      top: 20%;
      animation: slashStar @animationDuration ease-in-out 0.5s infinite;
    }
    &4 {
      left: 72%;
      top: 64%;
      animation: slashStar @animationDuration ease-in-out 0.8s infinite;
    }
    &5 {
      left: 87%;
      top: 30%;
      animation: slashStar @animationDuration ease-in-out 1.5s infinite;
    }
    }
    .vertical-middle {
      position: absolute;
      left: 0;
      top: 90px;
    }
    .product-area {
      width: 600px;
      margin-left: 40px;
    }
    .product-logo {
      display: block;
      //width: 257px;
      //height: 200px;
      margin: 0;
    }
    .product-desc {
      opacity: 0.8;
      font-family: Avenir-Medium;
      font-size: 24px;
      color: #fff;
      max-width: 780px;
      margin: 12px auto 30px;
      text-align: left;
    }
  }
}

</style>
