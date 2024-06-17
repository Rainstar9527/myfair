<template>
    <el-card class="box-card" id="card">

    <el-dialog :title="title" :visible.sync="dialogFormVisible" width="70%">
        <el-table :data="EmptylocationData" id="EmptylocationTable" :height="400" border>
            <el-table-column width="100">
                <template slot-scope="scope">
                    <el-radio
                        class="radio"
                        :label="scope.row"
                        v-model="currentLoc"
                    >&emsp;&emsp;&emsp;</el-radio>
                </template>
            </el-table-column>
            <el-table-column property="pubId" label="ID" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubCode" label="编号" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubLocation" label="位置" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubDesc" label="描述" align="center">
            </el-table-column>
        </el-table>
        <el-row style="margin-top: 40px;">
            <el-button type="primary" @click=addLocation>分配</el-button>
            <el-button type="danger" @click="dialogFormVisible = false">取消</el-button>
        </el-row>
    </el-dialog>

    <div class="container">
        <el-table :data="facData" id="facTable" :height="height" border>
            <el-table-column  width="55">

                <template slot-scope="scope">
                    <el-radio
                        class="radio"
                        :label="scope.row"
                        v-model="currentFac"
                    >&emsp;&emsp;&emsp;</el-radio>
                </template>

            </el-table-column>
            <el-table-column property="facId" v-if="false">
            </el-table-column>
            <el-table-column property="facName" label="名称" align="center">
            </el-table-column>
        </el-table>

        <el-table :data="locationData" id="locationTable" :height="height" border :row-class-name="tableRowClassName">
            <el-table-column width="50">
                <template slot-scope="scope">
                    <el-radio
                        class="radio"
                        :label="scope.row"
                        v-model="deleteLoc"
                    >&emsp;&emsp;&emsp;</el-radio>
                </template>
            </el-table-column>
            <el-table-column property="pubId" label="ID" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubCode" label="编号" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubLocation" label="位置" width="250" align="center">
            </el-table-column>
            <el-table-column property="pubDesc" label="描述" align="center">
            </el-table-column>
            <el-table-column property="facId" v-if="false">
            </el-table-column>
        </el-table>
    </div>
    <el-row style="margin-top: 40px;">
        <el-button type="primary" @click=handleAdd>分配展销位</el-button>
        <el-button type="danger" @click=deleteLocation>删除展销位</el-button>
    </el-row>
    </el-card>

</template>

<script>

export default {
    name: "factory",
    data() {
        return {
            currentFac: null,
            currentLoc: null,
            deleteLoc: null,
            facData: [],
            locationData: [],
            EmptylocationData: [],
            multipleSelection: [],
            dialogFormVisible: false,
            title: '请选择展销位',
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
        getEmptyLocList()
        {
            this.$axios({
                method: 'post',
                url: 'http://localhost:9090/getEmptyLocation',
            }).then(response => {
                this.EmptylocationData = response.data.data;
            })
        },
        handleAdd(){
            if (this.currentFac == null) {
                this.$message({
                    message: '请选择供应商',
                    type: 'error'
                })
            }else{
                this.dialogFormVisible = true;
                this.getEmptyLocList();
            }
        },
        addLocation()
        {
            var param = {
                pubId: this.currentLoc.pubId,
                pubCode: this.currentLoc.pubCode,
                pubLocation: this.currentLoc.pubLocation,
                pubDesc: this.currentLoc.pubDesc,
                facId: this.currentFac.facId
            }
            this.$axios({
            method: 'post',
            url: 'http://localhost:9090/updateLocation',
            data: param
            }).then(response => {
                this.dialogFormVisible = false
                this.getFacList()
                this.getLocList()
                this.$message({
                    message: response.data.message,
                    type: 'success'
                });
            })
        },
        deleteLocation()
        {
            var param = {
                pubId: this.deleteLoc.pubId,
                pubCode: this.deleteLoc.pubCode,
                pubLocation: this.deleteLoc.pubLocation,
                pubDesc: this.deleteLoc.pubDesc,
                facId: null
            }
            this.$axios({
            method: 'post',
            url: 'http://localhost:9090/updateLocation',
            data: param
            }).then(response => {
                this.dialogFormVisible = false
                this.getFacList()
                this.getLocList()
                this.$message({
                    message: response.data.message,
                    type: 'success'
                });
            })
        },
        tableRowClassName({ row, rowIndex }) {
            if (row.facId == null) {
                return 'success-row';
            }
            return '';
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

.el-table .success-row {
    background: #f0f9eb;
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