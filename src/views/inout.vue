<template>
    <el-card class="box-card" id="card">
        <el-header style="display: flex;">
            <div style="margin: 15px; width: 90%;">
                <el-input placeholder="请输入编号" v-model="input">
                <el-button slot="append" icon="el-icon-search" @click=getMember></el-button>
                </el-input>
            </div>
        </el-header>


        <el-table :data="tableData" id="tableData" :height="height">
            <el-table-column property="rid" label="ID" width="50" align="center">
            </el-table-column>
            <el-table-column property="member.mcode" label="编号" width="250" align="center">
            </el-table-column>
            <el-table-column property="member.mname" label="姓名" width="120" align="center">
            </el-table-column>
            <el-table-column property="recTime" label="时间" align="center">
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
            memberIn: 0,
            input: '',
            currentpage: 1,
            pagesize: 10,
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
            url: 'http://localhost:9090/getAllRecord',
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
        getMember(){
            this.$axios({
            method: 'post',
            url: 'http://localhost:9090/chooseMember',
            data: {
                mid: '',
                mcode: this.input,
                mname: '',
                mphone: '',
                mjob: '',
                mtype: '',
                facBuyId: '',
                mimage: '',
                mstate: 0
            }
            }).then(response => {
                this.memberIn = response.data.data.mid
                console.log(this.memberIn)
                this.add()
            })
        },
        add()
        {
            let date = new Date();
            this.$axios({
            method: 'post',
            url: 'http://localhost:9090/addRecord',
            data: {
                rid: '',
                buyerId: this.memberIn,
                recTime: date
            }
            }).then(response => {
                this.$message({
                    message: '记录成功',
                    type: 'success'
                })
                this.getList();
            })
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