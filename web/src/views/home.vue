<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
            <MailOutlined />
            <span>欢迎</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id">
          <template v-slot:title @click="handleClick">
            <span><user-outlined />{{item.name}}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined /><span>{{child.name}}</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
      >
      <div class="welcome" v-show="isShowWelcome">
        <h1>Welcom to Echst Wiki</h1>
      </div>
      <a-list v-show="!isShowWelcome" item-layout="vertical" size="large" :grid="{gutter: 20, column: 3}" :data-source="ebooks">
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
              <span v-for="{ type, text } in actions" :key="type">
                <component v-bind:is="type" style="margin-right: 8px" />
                {{ text }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <a :href="item.href">{{ item.name }}</a>
              </template>
              <template #avatar><a-avatar :src="item.cover" /></template>
            </a-list-item-meta>
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent, onMounted,ref} from 'vue';
import axios from 'axios';
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool";


export default defineComponent({
  name: 'Home',
  setup(){
    const  ebooks = ref();
    //const  ebooks1 = reactive({books: []});

    const level1 =  ref();
    const openKeys =  ref();
    let categorys: any;
    /**
     * 查询所有分类
     **/
    const handleQueryCategory = () => {
      axios.get("/category/all").then((response) => {
        const data = response.data;
        if (data.success) {
          categorys = data.content;
          console.log("原始数组：", categorys);
          openKeys.value = [];
          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);
        } else {
          message.error(data.message);
        }
      });
    };


    const isShowWelcome = ref(true);
    let categoryId2 = 0;

    const handleQueryEbook = () => {
      axios.get("/ebook/list", {
        params: {
          page: 1,
          size: 1000,
          categoryId2: categoryId2
        }
      }).then((response) => {
        const data = response.data;
        ebooks.value = data.content.list;
        // ebooks1.books = data.content;
      });
    };

    const handleClick = (value: any) => {
      if (value.key === 'welcome') {
        console.log("menu click", value)
        isShowWelcome.value = true;
      } else {
        categoryId2 = value.key;
        console.log("menu click", value)
        isShowWelcome.value = false;
        handleQueryEbook();
      }
      // isShowWelcome.value = value.key === 'welcome';
    };

    onMounted(() =>{
      handleQueryCategory();
        // axios.get("/ebook/list",{
        //   params:{
        //     page: 1,
        //     size: 1000
        //   }
        //
        // }).then((response)=>{
        //   const data = response.data;
        //   ebooks.value = data.content.list;
        //   //ebooks1.books = data.content;
        //   // console.log(response);
        // });
    })

    return {

      ebooks,
      // outBooks: toRef(ebooks1,"books"),
      // listData,
      pagination:{

        onChange: (page: any)=>{

          console.log(page);

        },
        pageSize: 3,
      },
      actions: [
        {type: "StarOutlined", text: "156"},
        {type: "LikeOutlined", text: "156"},
        {type: "MessageOutlined", text: "2"},
      ],

      handleClick,
      level1,
      isShowWelcome,

      openKeys,
    }

  }
});
</script>

<style scoped>
.ant-avatar {
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 8%;
  margin: 5px 0;
}
</style>