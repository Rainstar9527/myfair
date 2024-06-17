<template>
  <el-card class="box-card" id="card">

    <el-header style="display: flex;">
      <div style="margin: 15px; width: 90%;">
        <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
          <el-select v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="ID" value="1"></el-option>
            <el-option label="用户名" value="2"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
        </el-input>
      </div>

      <el-row style="margin: auto;">
        <el-button size="mini" type="primary" @click="add()">新增</el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="id:" hidden>
              <el-input v-model="form.aid"></el-input>
            </el-form-item>
            <el-form-item label="名称:" prop="adminName">
              <el-input v-model="form.adminName" placeholder="请输入管理员名称" style="width:80%"></el-input>
            </el-form-item>
            <el-form-item label="密码:" prop="adminPwd">
              <el-input v-model="form.adminPwd" placeholder="请输入管理员密码" style="width:80%"></el-input>
            </el-form-item>
            <el-form-item label="状态:" prop="adminState">
              <el-radio-group v-model="radio" style="width:80%">
                <el-radio :label="0">正常</el-radio>
                <el-radio :label="1">锁定</el-radio>
                <el-radio :label="2">注销</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleSubmit()">提 交</el-button>
          </div>
        </el-dialog>
      </el-row>


    </el-header>

    <el-table :data="tableData" id="tableData" :height="height" :row-class-name="tableRowClassName">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column property="aid" label="ID" width="50" align="center">
      </el-table-column>
      <el-table-column property="adminName" label="账户名" width="300" align="center">
      </el-table-column>
      <el-table-column property="adminPwd" label="密码(已加密)" width="400" align="center">
      </el-table-column>
      <el-table-column property="adminState" label="状态" align="center">

        <template slot-scope="scope">
          <p v-if="scope.row.adminState === 0">正常</p>
          <p v-else-if="scope.row.adminState === 1">锁定</p>
          <p v-else-if="scope.row.adminState === 2">注销</p>
        </template>

      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="edit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="remove(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-row style="margin-top: 20px;">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentpage"
        :page-sizes="[5, 10, 50, 100]" :page-size="pagesize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </el-row>
  </el-card>
</template>



<script>
export default {
  name: 'buyer',
  data() {
    return {

      total: 0,
      AllData: [],
      tableData: [],
      currentpage: 1,
      pagesize: 10,

      select: '',
      input: '',

      title: '',
      currentRow: null,
      dialogFormVisible: false,
      form: {},
      fileList: [],
      imageUrl: '',
      radio: 0,
      rules: {
        adminName: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        adminPwd: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      },
    }
  },
  methods: {
    handleSizeChange(size) {
      //修改当前每页的数据行数
      this.pagesize = size;
      //数据重新分页
      this.getPageInfo();
    },
    //调整当前的页码
    handleCurrentChange(pageNumber) {
      //修改当前的页码
      this.currentpage = pageNumber;
      //数据重新分页
      this.getPageInfo()
    },
    handleSuccess(response, file) {
      console.log(response.data)
      this.imageUrl = response.data
    },
    tableRowClassName({ row, rowIndex }) {
      if (row.adminState === 1) {
        return 'warning-row';
      } else if (row.adminState === 2) {
        return 'exit-row';
      }
      return '';
    },
    getList() {
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/getAllAccount',
      }).then(response => {
        this.AllData = response.data.data;
        this.total = response.data.data.length;
        this.getPageInfo();
      })
    },
    getPageInfo() {
      this.tableData = [];
      // 获取当前页的数据
      for (let i = (this.currentpage - 1) * this.pagesize; i < this.total; i++) {
        //把遍历的数据添加到pageTicket里面
        this.tableData.push(this.AllData[i]);
        //判断是否达到一页的要求
        if (this.tableData.length === this.pagesize) break;
      }
    },
    search() {
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/searchAccount',
        data: {
          selection: this.select,
          inputString: this.input
        }
      }).then(response => {
        this.AllData = response.data.data;
        this.total = response.data.data.length;
        this.getPageInfo();
      })
    },

    reset() {
      this.form = {
        aid: null,
        adminName: null,
        adminPwd: null,
      }
    },
    add() {
      this.reset()
      this.dialogFormVisible = true
      this.title = "新增管理员"
    },
    edit(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      this.form.adminPwd = null;
      this.dialogFormVisible = true
      this.title = "修改管理员数据"
    },
    remove(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      var param = {
        aid: this.form.aid
      }
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/removeAccount',
        data: param
      }).then(response => {
        this.getList();
        this.$message({
          message: response.data.message,
          type: 'success'
        });
      })
    },
    handleSubmit(){
      this.$refs['form'].validate((valid) => {
        if(valid){
          this.submit();
        }
      })
    },
    submit() {
      if (this.form.aid == null) {
        var param = {
          aid: '',
          adminName: this.form.adminName,
          adminPwd: this.form.adminPwd,
          adminState: this.radio
        }
        this.$axios({
          method: 'post',
          url: 'http://localhost:9090/addAccount',
          data: param
        }).then(response => {
          this.getList();
          this.dialogFormVisible = false
          this.$message({
            message: response.data.message,
            type: 'success'
          });
        })
      } else {
        var param = {
          aid: this.form.aid,
          adminName: this.form.adminName,
          adminPwd: this.form.adminPwd,
          adminState: this.radio
        }
        this.$axios({
          method: 'post',
          url: 'http://localhost:9090/editAccount',
          data: param
        }).then(response => {
          this.getList();
          this.dialogFormVisible = false
          this.$message({
            message: response.data.message,
            type: 'success'
          });
        })
      }
    },
    getHeight() {
      let num = document.getElementById("card").clientHeight - 150
      this.$nextTick(() => {
        this.height = String(num)
        console.log(this.height)
      })
    }
  },
  mounted() {
    this.getList();
    this.getHeight();
  }
}
</script>



<style>
.el-select .el-input {
  width: 130px;
}

.el-table .warning-row {
  background: #FFEFD5;
}

.el-table .exit-row {
  background: #FFC1C1;
}

.el-card {
  padding: 0%;
  height: 99%;
}
</style>