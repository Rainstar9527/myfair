<template>
    <el-card class="box-card" id="card">
    <div class="container">
        <el-table :data="facData" id="facTable" :height="height" :row-class-name="tableRowClassName">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column property="facName" label="名称" width="250" align="center">
            </el-table-column>
        </el-table>

        <el-table :data="locationData" id="locationTable" :height="height" :row-class-name="tableRowClassName">
            <el-table-column type="selection" width="50">
            </el-table-column>
            <el-table-column property="pubId" label="ID" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubCode" label="编号" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubLocation" label="位置" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubDesc" label="描述" width="400" align="center">
            </el-table-column>
        </el-table>
    </div>
    </el-card>

</template>

<script>

export default {
    name: "factory",
    data() {
        return {
            facData: [],

            //分页相关
            total: 0,
            locationData: [],
            //查
            select: '',
            input: '',


            height: '200px'
        }
    },
    methods: {
        //获取全部数据，在前端进行分页
        getFacList() {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getAllFactory',
            }).then(response => {
                this.facData = response.data.data;
            })
        },
        getLocList()
        {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getAllLocation',
            }).then(response => {
                this.locationData = response.data.data;
            })
        },
        //搜索相关
        search() {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/searchFactory',
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

        // 新增
        // 表单重置初始化
        reset() {
            this.form = {
                id: null,
                facName: null,
                facDesc: null,
                facAddress: null,
                facPhone: null,
            },
                this.imageUrl = ''
        },
        add() {
            this.reset()
            this.dialogFormVisible = true
            this.title = "新增厂商数据"
        },
        handleSuccess(response, file) {
            console.log(response.data)
            this.imageUrl = response.data
        },

        edit(index, row) {
            this.reset()
            this.form = JSON.parse(JSON.stringify(row));
            this.imageUrl = this.form.facLogo
            this.dialogFormVisible = true
            this.title = "修改厂商数据"
        },

        remove(index, row) {
            this.reset()
            this.form = JSON.parse(JSON.stringify(row));
            var param = {
                facId: this.form.facId
            }
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/removeFactory',
                data: param
            }).then(response => {
                this.getList();
                this.$message({
                    message: response.data.message,
                    type: 'success'
                });
            })
        },
        handleSubmit() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    this.submit();
                }
            })
        },

        //提交按钮
        submit() {
            if (this.form.facId == null) {
                var param = {
                    facId: '',
                    facName: this.form.facName,
                    facDesc: this.form.facDesc,
                    facAddress: this.form.facAddress,
                    facPhone: this.form.facPhone,
                    facLogo: this.imageUrl,
                    facState: this.radio
                }
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:9090/addFactory',
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
                    facId: this.form.facId,
                    facName: this.form.facName,
                    facDesc: this.form.facDesc,
                    facAddress: this.form.facAddress,
                    facPhone: this.form.facPhone,
                    facLogo: this.form.facLogo,
                    facState: this.radio
                }
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:9090/editFactory',
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
        this.getFacList();
        this.getLocList();
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

.container {
    display: flex;
    height: 100%;
}

#facTable {
    display: inline;
    flex: 2;
}

#locationTable {
    display: inline;
    flex: 8;
}
</style>