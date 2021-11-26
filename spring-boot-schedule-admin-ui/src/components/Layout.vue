<template>
  <div>
    <el-container class="layout">
      <el-container class="main">
        <el-header class="header">
          <el-container>
            <div class="header-left">
              <div class="icon">
              </div>
              <div style="width: 500px; line-height: 60px;"><span style="font-size:50px">SSA</span></div>
            </div>
            <div class="header-right">
              <a href="https://gitee.com/plumeorg/plumejob" target="_blank"> 主 页</a>
              <a href="https://gitee.com/plumeorg/plumejob" target="_blank"> Version 1.0</a>
            </div>
          </el-container>
        </el-header>
        <el-main class="content">
          <el-scrollbar class="scroll">
            <el-card >
              <div slot="header" class="clearfix">
                <span>任务</span>
              </div>
              <el-table
                  :data="jobList"
                  style="width: 100%">

                <el-table-column
                    prop="jobName"
                    label="任务名称">
                </el-table-column>
                <el-table-column
                    prop="status"
                    width="210"
                    label="运行状态">
                  <template slot-scope="scope">
                    <el-tag v-if="scope.row.status === 'running'">运行中</el-tag>
                    <el-tag v-if="scope.row.status === 'suspend'" type="danger">暂停</el-tag>
                  </template>
                </el-table-column>
                <el-table-column
                    width="300"
                    prop="expression"
                    label="Expression" >
                  <template slot-scope="scope">
                    <el-input v-model="scope.row.expression" auto-complete="off">
                      <el-popover
                          slot="append"
                          placement="right"
                          width="800"
                          trigger="click">
                        <cron :key="scope.jobName"  v-model="scope.row.expression"/>
                        <el-button slot="reference" v-if="!scope.row.showCronBox" icon="el-icon-arrow-up" @click="scope.row.showCronBox  = true" title="打开图形配置"></el-button>
                        <el-button slot="reference" v-else icon="el-icon-arrow-down" @click="scope.row.showCronBox  = false" title="关闭图形配置"></el-button>
                      </el-popover>
                    </el-input>
                  </template>
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="210">
                  <template slot-scope="scope">
                    <el-button @click="run(scope.row)" type="text" size="small">立即执行</el-button>
                    <el-button @click="update(scope.row)" type="text" size="small">更 新</el-button>
                    <el-button v-if="scope.row.status === 'running'" @click="suspend(scope.row)" type="text" size="small">暂 停</el-button>
                    <el-button v-if="scope.row.status === 'suspend'"  @click="start(scope.row)" type="text" size="small">开 启</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-card>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import {listJob, run, suspend, start, update} from "@/api";
import Cron from "@/components/cron";

export default {
  name: 'App',
  components: {Cron},
  data: function () {
    return {
      jobList: []
    }
  },
  methods: {
    run(item) {
      this.$confirm('确定立即执行, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        run(item.jobName).then(res => {
          this.list();
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        })
      })
    },update(item) {
      this.$confirm('确定更新该任务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        update(item).then(res => {
          this.list();
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        })
      })
    },
    suspend(item) {
      this.$confirm('确定暂停该任务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        suspend(item.jobName).then(res => {
          this.list();
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        })
      })
    },
    start(item) {
      this.$confirm('确定开启该任务, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        start(item.jobName).then(res => {
          this.list();
          this.$message({
            type: 'success',
            message: '操作成功!'
          });
        })
      })

    },
    list() {
      listJob().then(res => {
        res.forEach(e => e.showCronBox = false)
        this.jobList = res
      })
    }
  },
  mounted() {
    this.list()
  },
}
</script>

<style lang="less">
.el-progress {
  width: 85%;
}

.item {
  display: flex;
  margin: 10px 0;

  .item-label {
    width: 50px;
  }
}
</style>
