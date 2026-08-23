<template>
  <div class="pay-container">
    <!-- 配送方式选择 -->
    <div class="delivery-type-box">
      <span class="label">取货方式：</span>
      <el-radio-group v-model="deliveryType" size="small">
        <el-radio :label="1">配送</el-radio>
        <el-radio :label="2">自提</el-radio>
      </el-radio-group>
    </div>

    <!-- 收货地址（仅配送时显示） -->
    <div v-if="deliveryType === 1" class="address-box" @click="selectAddress">
      <div v-if="addressBook" class="address-info">
        <div class="address-top">
          <span class="consignee">{{ addressBook.consignee }}</span>
          <span class="phone">{{ addressBook.phone }}</span>
        </div>
        <div class="address-detail">{{ addressBook.provinceName }}{{ addressBook.cityName }}{{ addressBook.districtName }}{{ addressBook.detail }}</div>
      </div>
      <div v-else class="address-placeholder">
        <span>请选择收货地址</span>
        <i class="el-icon-arrow-right"></i>
      </div>
    </div>

    <!-- 自提提示 -->
    <div v-if="deliveryType === 2" class="pickup-tip">
      <i class="el-icon-location-outline"></i>
      <span>请到店自取，下单后可在订单详情查看取餐码</span>
    </div>

    <!-- 商品清单 -->
    <div class="goods-list">
      <div class="goods-item" v-for="item in shopCart" :key="item.id">
        <image :src="item.image" class="goods-img" mode="aspectFill"></image>
        <div class="goods-info">
          <div class="goods-name">{{ item.name }}</div>
          <div class="goods-desc" v-if="item.dishFlavor">{{ item.dishFlavor }}</div>
          <div class="goods-bottom">
            <span class="goods-price">￥{{ item.amount }}</span>
            <span class="goods-number">x{{ item.number }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 配送费 -->
    <div class="fee-row">
      <span>配送费</span>
      <span>￥{{ deliveryFee }}</span>
    </div>

    <!-- 备注 -->
    <div class="remark-box">
      <span class="label">备注：</span>
      <input type="text" v-model="remark" placeholder="口味、偏好等要求" class="remark-input" />
    </div>

    <!-- 支付方式 -->
    <div class="pay-method-box">
      <span class="label">支付方式：</span>
      <el-radio-group v-model="payMethod" size="small">
        <el-radio :label="1">微信支付</el-radio>
        <el-radio :label="2">支付宝</el-radio>
      </el-radio-group>
    </div>

    <!-- 底部结算栏 -->
    <div class="pay-footer">
      <div class="total-price">
        合计：<span class="price">￥{{ totalAmount }}</span>
      </div>
      <button class="submit-btn" @click="submitOrder">去支付</button>
    </div>
  </div>
</template>

<script>
import { submitOrderApi } from '@/api/order'
import { getShoppingCart } from '@/api/shoppingCart'

export default {
  name: 'Pay',
  data() {
    return {
      // 取货方式：1配送 2自提，默认配送
      deliveryType: 1,
      // 收货地址
      addressBook: null,
      // 购物车数据
      shopCart: [],
      // 备注
      remark: '',
      // 支付方式，默认微信支付
      payMethod: 1,
      // 配送费
      deliveryFee: 5
    }
  },
  computed: {
    // 商品总金额
    goodsAmount() {
      return this.shopCart.reduce((sum, item) => {
        return sum + Number(item.amount) * item.number
      }, 0)
    },
    // 订单总金额（自提免配送费）
    totalAmount() {
      if (this.deliveryType === 2) {
        return this.goodsAmount.toFixed(2)
      }
      return (this.goodsAmount + this.deliveryFee).toFixed(2)
    }
  },
  created() {
    this.loadShoppingCart()
  },
  methods: {
    // 加载购物车
    async loadShoppingCart() {
      try {
        const res = await getShoppingCart()
        this.shopCart = res.data || []
      } catch (e) {
        console.error('加载购物车失败', e)
      }
    },
    // 选择收货地址
    selectAddress() {
      // 跳转到地址选择页，选择后通过回调或vuex更新addressBook
      this.$router.push({
        path: '/address/select',
        query: { from: 'pay' }
      })
    },
    // 提交订单
    async submitOrder() {
      // 配送模式校验：必须选择收货地址
      if (this.deliveryType === 1) {
        if (!this.addressBook || !this.addressBook.id) {
          this.$message.warning('请选择收货地址')
          return
        }
      }

      // 购物车为空校验
      if (!this.shopCart || this.shopCart.length === 0) {
        this.$message.warning('购物车为空')
        return
      }

      // 组装请求参数
      const params = {
        // 配送时传地址ID，自提时传null
        addressBookId: this.deliveryType === 1 ? this.addressBook.id : null,
        payMethod: this.payMethod,
        remark: this.remark,
        // 取货方式：1配送 2自提
        deliveryType: this.deliveryType
      }

      try {
        const res = await submitOrderApi(params)
        // 提交成功，跳转到支付/订单详情
        this.$message.success('下单成功')
        this.$router.push({
          path: '/order/detail',
          query: { id: res.data.id }
        })
      } catch (e) {
        this.$message.error(e.message || '下单失败')
      }
    }
  }
}
</script>
