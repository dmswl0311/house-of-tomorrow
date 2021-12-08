<template>
    <div>
        <CommentList  :propsdata="propsdata" ></CommentList>
        <CommentWrite  :propsdata="propsdata"></CommentWrite>
    </div>
</template>
<script>
import CommentWrite from "@/components/comment/CommentWrite"
import CommentList from "@/components/comment/CommentList"
export default {
    name: 'Comment',
    components:{
        // CommentWirte,
        CommentList,
        CommentWrite
    },
     props: {
          propsdata: Number,
    },
    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
    
    },
};
</script>

<style lang="scss" scoped>

</style>