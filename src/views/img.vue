<template>
  <div>
      <div>
          <el-upload
                  class="upload-demo"
                  ref="upload"
                  action="http://localhost:9090/addimg"
                  :on-success="handleSuccess"
                  multiple
                  :file-list="fileList">
              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          </el-upload>
      </div>
      <div>
          <h3>这是你上传的图片：</h3>
          <img v-if="imageUrl!==''" :src="imageUrl" style="width: 400px; height: 400px; margin-top: 10px">
          <span v-else>暂未收到图片</span>
          <p>{{ imageUrl }}</p>
      </div>
  </div>
</template>


<script>
export default {
    data() {
        return {
            fileList: [],
            imageUrl: ''
        }
    },
    methods: {
        handleSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        submitUpload() {
            this.$refs.upload.submit();
            this.fileList = [];
        }
    }
}
</script>
