<template>
    <el-card class="box-card">
      <!-- 表格 -->
      <el-table
        ref="singleTable"
        :data="tableData"
        style="width: 100%">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          property="facId"
          label="ID"
          width="50"
          align="center">
        </el-table-column>
        <el-table-column
          property="facName"
          label="名称"
          width="120"
          align="center">
        </el-table-column>
        <el-table-column
          property="facDesc"
          label="类型"
          width="120"
          align="center">
        </el-table-column>
        <el-table-column
          property="facAddress"
          label="地址"
          width="120"
          align="center">
        </el-table-column>
        <el-table-column
          property="facPhone"
          label="电话"
          align="center">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="edit(scope.$index, scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="remove(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </template>
   
  <script>
import { name } from 'file-loader';

    export default {
      name: "student",
      data() {
        return {
          name: '',
          title: '',
          currentRow: null,
          dialogFormVisible: false,
          form: {},
          tableData: []
        }
      },
      methods: {
        getList() {
          this.$axios({
            method: 'post',
            url: 'http://localhost:9090/getAllFactory',
          }).then(response => {
            this.tableData = response.data.data
          })
        },
      },
      mounted() {
        this.getList();
      }
    }
  </script>
   
  <style scoped>
  </style>