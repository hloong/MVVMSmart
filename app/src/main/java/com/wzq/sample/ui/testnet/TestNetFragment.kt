package com.wzq.sample.ui.testnet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.gson.reflect.TypeToken
import com.wzq.mvvmsmart.net.base.BaseResponse
import com.wzq.mvvmsmart.net.net_utils.GsonUtil
import com.wzq.mvvmsmart.utils.KLog
import com.wzq.sample.BR
import com.wzq.sample.R
import com.wzq.sample.base.BaseFragment
import com.wzq.sample.bean.BaseUrlData
import com.wzq.sample.bean.NewsData
import com.wzq.sample.databinding.FragmentTestNetBinding
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import java.lang.Exception

/**
 * Create Date：2019/01/25
 * Description：RecycleView多布局实现
 */
class TestNetFragment : BaseFragment<FragmentTestNetBinding, TestNetViewModel>() {
    override fun initContentView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): Int {
        return R.layout.fragment_test_net
    }

    override fun initVariableId(): Int {
        return BR.viewModel
    }

    override fun initViewObservable() {
        super.initViewObservable()
        binding.button.setOnClickListener { v: View? ->
            KLog.e("发起请求")
            viewModel.doPostServerNews() // 请求网络数据;
        }
        viewModel.liveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                binding.tvJson.text = it[0].news_summary
            }
        })


        binding.buttonBaseUrl.setOnClickListener {
            //只针对该接口动态使用https://api.apiopen.top，其他接口依旧采用全局BaseUrl，更多用法参考 https://github.com/JessYanCoding/RetrofitUrlManager
            RetrofitUrlManager.getInstance().putDomain("api","https://api.apiopen.top")
            viewModel.doBaseUrl()
        }
        viewModel.liveDataBaseUrl.observe(this, Observer {
            try {
                KLog.d(it)
                if (it != null){
                    binding.tvJson.text = it.message+"--"+it.code
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        })

    }
}