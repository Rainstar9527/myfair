<template>
    <el-card class="box-card">
      <!-- 表格 -->
      <el-head>
        <div style="margin: 15px;">
          <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择">
              <el-option label="公司名" value="1"></el-option>
              <el-option label="手机号" value="2"></el-option>
              <el-option label="公司类型" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
          </el-input>
        </div>
      </el-head>
      <el-table
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
      <el-row>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentpage"
          :page-sizes="[5, 10, 50, 100]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </el-row>

    </el-card>

  </template>
   
  <script>

    export default {
      name: "student",
      data() {
        return {
          name: '',
          title: '',
          //增删
          currentRow: null,
          dialogFormVisible: false,
          form: {},
          //分页相关
          total: 0,
          AllData: [],
          tableData: [],
          currentpage: 1,
          pagesize: 10,
          //查
          select: '',
          input: ''
        }
      },
      methods: {
        //获取全部数据，在前端进行分页
        getList() {
          this.$axios({
            method: 'post',
            url: 'http://localhost:9090/getAllFactory',
          }).then(response => {
            this.AllData = response.data.data;
            this.total = response.data.data.length;            
            this.getPageInfo();
          })
        },
        getPageInfo(){
          this.tableData=[];
          // 获取当前页的数据
          for(let i=(this.currentpage-1)*this.pagesize;i<this.total;i++){
            //把遍历的数据添加到pageTicket里面
            this.tableData.push(this.AllData[i]);
            //判断是否达到一页的要求
            if(this.tableData.length===this.pagesize) break;
          }
        },
            //分页时修改每页的行数,这里会自动传入一个size
        handleSizeChange(size){
          //修改当前每页的数据行数
          this.pagesize=size;
          //数据重新分页
          this.getPageInfo();
        },
        //调整当前的页码
        handleCurrentChange(pageNumber){
          //修改当前的页码
          this.currentpage=pageNumber;
          //数据重新分页
          this.getPageInfo()
        },

        //搜索相关
        search(){
          var param = {}
          this.$axios({
            method: 'post',
            url: 'http://localhost:9090/searchFactory',
            data: {
              selection: this.select,
              inputString: this.input
            }
          }).then(response=>{
            this.AllData = response.data.data;
            this.total = response.data.data.length;            
            this.getPageInfo();
          })
        }
      },
      mounted() {
        this.getList();
      }
    }
  </script>
   
  <style>
    .el-select .el-input {
      width: 130px;
    }

  </style>