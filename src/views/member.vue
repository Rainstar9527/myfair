<template>
    <el-card class="box-card" id="card">
        <el-header style="display: flex;">
            <div style="margin: 15px; width: 90%;">
                <el-input placeholder="请输入内容" v-model="input" class="input-with-select">
                    <el-select v-model="select" slot="prepend" placeholder="请选择">
                        <el-option label="姓名" value="1"></el-option>
                        <el-option label="电话" value="2"></el-option>
                        <el-option label="编号" value="3"></el-option>
                    </el-select>
                    <el-button slot="append" icon="el-icon-search" @click="search()"></el-button>
                </el-input>
            </div>

            <el-row style="margin: auto;">
                <el-button size="mini" type="primary" @click="add()">新增</el-button>
                <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
                    <el-form :model="form" :rules="rules" ref="form">
                        <el-form-item label="id:" hidden>
                            <el-input v-model="form.mid"></el-input>
                        </el-form-item>
                        <el-form-item label="编号:" prop="mcode">
                            <el-input v-model="form.mcode" placeholder="请输入会员编号" style="width:80%"></el-input>
                        </el-form-item>
                        <el-form-item label="名称:" prop="mname">
                            <el-input v-model="form.mname" placeholder="请输入会员名称" style="width:80%"></el-input>
                        </el-form-item>
                        <el-form-item label="电话:" prop="mphone">
                            <el-input v-model="form.mphone" placeholder="请输入会员电话" style="width:80%"></el-input>
                        </el-form-item>
                        <el-form-item label="职位:" prop="mjob">
                            <el-input v-model="form.mjob" placeholder="请输入会员职位" style="width:80%"></el-input>
                        </el-form-item>
                        <el-form-item label="类型:" prop="mtype">
                            <el-radio-group v-model="radio" style="width:80%" @change="radioChange">
                                <el-radio :label="0">厂商</el-radio>
                                <el-radio :label="1">采购方</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="公司名称" prop="facBuyId" v-if="this.radio === 0">
                            <el-select v-model="form.facBuyId" placeholder="请选择活动区域">
                                <el-option v-for="item in options" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="公司名称" prop="facBuyId" v-if="this.radio === 1">
                            <el-select v-model="form.facBuyId" placeholder="请选择活动区域">
                                <el-option v-for="item in options" :key="item.value" :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="上传图片" prop="imageUrl">
                            <el-upload class="upload-demo" action="http://localhost:9090/addImg"
                                :on-success="handleSuccess" multiple :limit="1" :file-list="fileList">
                                <el-button size="small" type="primary">点击上传</el-button>
                            </el-upload>
                            <img v-if="imageUrl !== ''" :src="'http://localhost:9090/' + imageUrl"
                                style="width: 25%; height: 25%; margin-top: 10px">
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="dialogFormVisible = false">取 消</el-button>
                        <el-button type="primary" @click="handleSubmit()">提 交</el-button>
                    </div>
                </el-dialog>
            </el-row>


        </el-header>

        <el-table :data="tableData" id="tableData" :height="height" >
            <el-table-column property="mid" label="ID" width="50" align="center">
            </el-table-column>
            <el-table-column property="mcode" label="编号" width="100" align="center">
            </el-table-column>
            <el-table-column property="mname" label="姓名" width="200" align="center">

                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top" style="width: 100px; height: 100px;">
                        <img v-if="scope.row.mimage !== ''" :src="'http://localhost:9090/' + scope.row.mimage"
                            style="width: 100%; height: 150px;">
                        <div slot="reference" class="name-wrapper">
                            <el-tag size="medium">{{ scope.row.mname }}</el-tag>
                        </div>
                    </el-popover>
                </template>

            </el-table-column>
            <el-table-column property="mphone" label="电话" width="200" align="center">
            </el-table-column>
            <el-table-column property="mimage" label="logo" width="120" align="center" v-if="false">
            </el-table-column>
            <el-table-column property="mjob" label="职位" width="150" align="center">
            </el-table-column>
            <el-table-column property="mtype" label="类型" width="100" align="center">

                <template slot-scope="scope">
                    <p v-if="scope.row.mtype === 0">厂商</p>
                    <p v-else-if="scope.row.mtype === 1">采购商</p>
                </template>

            </el-table-column>
            <el-table-column property="mstate" label="状态" align="center">

                <template slot-scope="scope">
                    <p v-if="scope.row.mstate === 0">正常</p>
                    <p v-else-if="scope.row.mstate === 1">注册</p>
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
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentpage" :page-sizes="[5, 10, 50, 100]" :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
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

            options: [],
            ComData: {},
            Comtotal: 0,
            title: '',
            currentRow: null,
            dialogFormVisible: false,
            form: {},
            fileList: [],
            imageUrl: '',
            radio: 0,
            rules: {
                mcode: [{ required: true, message: '请输入编号', trigger: 'blur' }],
                mname: [{ required: true, message: '请输入名称', trigger: 'blur' }],
                mphone: [{ required: true, message: '请输入电话', trigger: 'blur' }],
                mjob: [{ required: true, message: '请输入职位', trigger: 'blur' }],
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
        getList() {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getAllMember',
            }).then(response => {
                this.AllData = response.data.data;
                this.total = response.data.data.length;
                this.getPageInfo();
            })
        },
        radioChange: function (val) {
            if (val === 0) this.getFacList();
            else this.getBuyList();
        },
        getFacList() {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getAllFactory',
            }).then(response => {
                this.ComData = response.data.data;
                this.Comtotal = response.data.data.length;
                this.options = [];
                for (let i = 0; i < this.Comtotal; i++) {
                    var param = {
                        value: this.ComData[i].facId,
                        label: this.ComData[i].facName,
                    }
                    this.options.push(param);
                }
            })
        },
        getBuyList() {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getAllBuy',
            }).then(response => {
                this.ComData = response.data.data;
                this.Comtotal = response.data.data.length;
                this.options = [];
                for (let i = 0; i < this.Comtotal; i++) {
                    var param = {
                        value: this.ComData[i].buyId,
                        label: this.ComData[i].buyName,
                    }
                    this.options.push(param);
                }
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
                url: 'http://localhost:9090/searchMember',
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
                mid: null,
                mcode: null,
                mname: null,
                mphone: null,
                mjob: null,
            },
                this.imageUrl = ''
        },
        add() {
            this.reset()
            this.dialogFormVisible = true
            this.title = "新增会员数据"
        },
        edit(index, row) {
            this.reset()
            this.form = JSON.parse(JSON.stringify(row));
            this.imageUrl = this.form.mimage
            this.radio = this.form.mtype
            if (this.radio == 0) this.getFacList();
            else this.getBuyList();
            this.dialogFormVisible = true
            this.title = "修改会员数据"
        },
        remove(index, row) {
            this.reset()
            this.form = JSON.parse(JSON.stringify(row));
            var param = {
                mid: this.form.mid
            }
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/removeMember',
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
            if (this.form.mid == null) {
                var param = {
                    mid: '',
                    mcode: this.form.mcode,
                    mname: this.form.mname,
                    mphone: this.form.mphone,
                    mjob: this.form.mjob,
                    mtype: this.radio,
                    facBuyId: this.form.facBuyId,
                    mimage: this.imageUrl,
                    mstate: 0
                }
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:9090/addMember',
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
                    mid: this.form.mid,
                    mcode: this.form.mcode,
                    mname: this.form.mname,
                    mphone: this.form.mphone,
                    mjob: this.form.mjob,
                    mtype: this.radio,
                    facBuyId: this.form.facBuyId,
                    mimage: this.imageUrl,
                    mstate: 0
                }
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:9090/editMember',
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