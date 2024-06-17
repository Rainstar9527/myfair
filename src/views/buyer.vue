<template>
  <el-card class="box-card" id="card">

    <el-header style="display: flex;">
      <div style="margin: 15px; width: 90%;">
        <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
          <el-select v-model="select" slot="prepend" placeholder="请选择">
            <el-option label="公司名" value="1"></el-option>
            <el-option label="公司地址" value="2"></el-option>
          </el-select>
          <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
        </el-input>
      </div>

      <el-row style="margin: auto;">
        <el-button size="mini" type="primary" @click="add()">新增</el-button>
        <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form" :rules="rules" ref="form">
            <el-form-item label="id:" hidden>
              <el-input v-model="form.id"></el-input>
            </el-form-item>
            <el-form-item label="名称:" prop="buyName">
              <el-input v-model="form.buyName" placeholder="请输入采购商名称" style="width:80%"></el-input>
            </el-form-item>
            <el-form-item label="类型:" prop="buyDesc">
              <el-input v-model="form.buyDesc" placeholder="请输入采购商描述" style="width:80%"></el-input>
            </el-form-item>
            <el-form-item label="地址:" prop="buyAddress">
              <el-input v-model="form.buyAddress" placeholder="请输入采购商地址" style="width:80%"></el-input>
            </el-form-item>
            <el-form-item label="上传图片" prop="imageUrl">
              <el-upload class="upload-demo" action="http://localhost:9090/addImg" :on-success="handleSuccess" multiple
                :limit="1" :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
              <img v-if="imageUrl !== ''" :src="'http://localhost:9090/' + imageUrl"
                style="width: 25%; height: 25%; margin-top: 10px">
            </el-form-item>
            <el-form-item label="状态:" prop="buyState">
              <el-radio-group v-model="radio" style="width:80%">
                <el-radio :label="0">正常</el-radio>
                <el-radio :label="1">注销</el-radio>
                <el-radio :label="2">退出</el-radio>
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
      <el-table-column property="buyId" label="ID" width="50" align="center">
      </el-table-column>
      <el-table-column property="buyName" label="名称" width="250" align="center">

        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top" style="width: 100px; height: 100px;">
            <img v-if="scope.row.buyLogo !== ''" :src="'http://localhost:9090/' + scope.row.buyLogo"
              style="width: 100%; height: 150px;">
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.buyName }}</el-tag>
            </div>
          </el-popover>
        </template>

      </el-table-column>
      <el-table-column property="buyDesc" label="描述" width="120" align="center">
      </el-table-column>
      <el-table-column property="buyLogo" label="logo" width="120" align="center" v-if="false">
      </el-table-column>
      <el-table-column property="buyAddress" label="地址" width="250" align="center">
      </el-table-column>
      <el-table-column property="buyState" label="状态" align="center">

        <template slot-scope="scope">
          <p v-if="scope.row.buyState === 0">正常</p>
          <p v-else-if="scope.row.buyState === 1">注销</p>
          <p v-else-if="scope.row.buyState === 2">退出</p>
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
        buyName: [{ required: true, message: '请输入名称', trigger: 'blur' }],
        buyDesc: [{ required: true, message: '请输入类型', trigger: 'blur' }],
        buyAddress: [{ required: true, message: '请输入地址', trigger: 'blur' }],
      },
      height: '200px'
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
      if (row.buyState === 1) {
        return 'warning-row';
      } else if (row.buyState === 2) {
        return 'exit-row';
      }
      return '';
    },
    getList() {
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/getAllBuy',
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
        url: 'http://localhost:9090/searchBuy',
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
        id: null,
        buyName: null,
        buyDesc: null,
        buyAddress: null,
      },
        this.imageUrl = ''
    },
    add() {
      this.reset()
      this.dialogFormVisible = true
      this.title = "新增厂商数据"
    },
    edit(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      this.imageUrl = this.form.buyLogo
      this.dialogFormVisible = true
      this.title = "修改厂商数据"
    },
    remove(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      var param = {
        buyId: this.form.buyId
      }
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/removeBuy',
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
      if (this.form.buyId == null) {
        var param = {
          buyId: '',
          buyName: this.form.buyName,
          buyDesc: this.form.buyDesc,
          buyAddress: this.form.buyAddress,
          buyLogo: this.imageUrl,
          buyState: this.radio
        }
        this.$axios({
          method: 'post',
          url: 'http://localhost:9090/addBuy',
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
          buyId: this.form.buyId,
          buyName: this.form.buyName,
          buyDesc: this.form.buyDesc,
          buyAddress: this.form.buyAddress,
          buyLogo: this.imageUrl,
          buyState: this.radio
        }
        this.$axios({
          method: 'post',
          url: 'http://localhost:9090/editBuy',
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