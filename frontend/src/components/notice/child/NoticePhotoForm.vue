<template>
      <b-row class="my-1">
       <b-col sm="4">
           <label for="">사진 선택</label>
       </b-col>
      <b-col sm="8">
          <form method="post" enctype="multipart/form-data">
             <input type="file" @change="thumbnail" ref="file"/>
          </form>
      </b-col> 
    </b-row>
</template>

<script>

export default {
  name: "NoticePhotoForm",
  data() {
    return {
      title:"사진 추가",
      newPhoto:"",
       };
  },
  method:{
    ok(){
      console.log("ok");
    }
  }
};
</script>

<style></style>
