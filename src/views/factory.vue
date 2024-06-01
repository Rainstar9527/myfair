<template>
    <el-card class="box-card">
      <el-header style="display: flex;">
        <!-- 搜索 -->
        <div style="margin: 15px; width: 90%;">
          <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择">
              <el-option label="公司名" value="1"></el-option>
              <el-option label="手机号" value="2"></el-option>
              <el-option label="公司类型" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
          </el-input>
        </div>

        <!-- Dialog 对话框 弹出新增和修改表单 -->
        <el-row style="margin: auto;">
          <el-button size="mini" type="primary" @click="add">新增</el-button>
          <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
            <el-form :model="form" :rules="rules" ref="form">
              <el-form-item label="id:" hidden>
                <el-input v-model="form.id"></el-input>
              </el-form-item>
              <el-form-item label="名称:" prop="facName">
                <el-input v-model="form.facName" placeholder="请输入厂商名称" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="类型:" prop="facDesc">
                <el-input v-model="form.facDesc" placeholder="请输入厂商类型" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="地址:" prop="facAddress">
                <el-input v-model="form.facAddress" placeholder="请输入厂商地址" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="电话:" prop="facPhone">
                <el-input v-model="form.facPhone" placeholder="请输入厂商电话" style="width:80%"></el-input>
              </el-form-item>
              <el-form-item label="上传图片"  prop="imageUrl">
                <el-upload
                  class="upload-demo"
                  action="http://localhost:9090/addImg"
                  :on-success="handleSuccess"
                  multiple
                  :limit="1"
                  :file-list="fileList">
                  <el-button size="small" type="primary" >点击上传</el-button>
                </el-upload>
                <img v-if="imageUrl!==''" :src="imageUrl" style="width: 25%; height: 25%; margin-top: 10px">
              </el-form-item>
              <el-form-item label="状态:" prop="facState">
                <el-radio-group v-model="radio" style="width:80%">
                  <el-radio :label="0">正常</el-radio>
                  <el-radio :label="1">注销</el-radio>
                  <el-radio :label="2">退出</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="submit()">提 交</el-button>
            </div>
          </el-dialog>
        </el-row>

      </el-header>

      <!-- 表格 -->
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
      <!-- 分页 -->
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
      name: "factory",
      data() {
        return {
          //增删
          title: '',
          currentRow: null,
          dialogFormVisible: false,
          form: {},
          fileList: [],
          imageUrl: '',
          radio: 0,
          rules: {
            facName: [{required: true, message: '请输入名称', trigger: 'blur'}],
            facDesc: [{required: true, message: '请输入类型', trigger: 'blur'}],
            facAddress: [{required: true, message: '请输入地址', trigger: 'blur'}],
            facPhone: [{required: true, message: '请输入电话', trigger: 'blur'}]
          },
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
        },

        // 新增
        // 表单重置初始化
        reset() {
          this.form = {
            id: null,
            name: null,
            age: null,
            gender: null,
            email: null
          }
        },
        add(){
          this.reset()
          this.dialogFormVisible=true
          this.title="新增厂商数据"
        },
        handleSuccess(response, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },

      //提交按钮
      submit() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            if (this.form.id == null) {
              this.$axios({
                method: 'post',
                data: this.form,
                url: 'http://localhost:9090/student/add',
              }).then((response) => {
                this.$message({
                  message: '新增成功!',
                  type: 'success'
                });
                this.dialogFormVisible = false
                this.getList();
              }).catch((error) => {
              })
            } else {
              this.$axios({
                method: 'post',
                data: this.form,
                url: 'http://localhost:9090/student/edit',
              }).then((response) => {
                this.$message({
                  message: '修改成功!',
                  type: 'success'
                });
                this.getList();
                this.dialogFormVisible = false
              }).catch((error) => {
              })
            }
          } else {
            return false;
          }
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