package com.ontheroad.mysql.impl.InformationImpl;

import com.ontheroad.mysql.Mapper.InformationMapper.InformationMapper;
import com.ontheroad.mysql.dao.InformationUserMapper;
import com.ontheroad.mysql.dao.LunboMapper;
import com.ontheroad.mysql.dao.TbInformationMapper;
import com.ontheroad.mysql.entity.InformationUser;
import com.ontheroad.mysql.entity.Lunbo;
import com.ontheroad.mysql.entity.LunboExample;
import com.ontheroad.mysql.entity.TbInformation;
import com.ontheroad.mysql.entity.TbInformationExample;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.pojo.Information.*;
import com.ontheroad.service.InformationService.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class InformationImpl implements InformationService{

	@Autowired
	private InformationMapper informationMapper;
	@Autowired
	private TbInformationMapper tbInformationMapper;
	@Autowired
	private LunboMapper lunboMapper;
	@Autowired
	private InformationUserMapper informationUserMapper;
	
	
	@Override
	public Map<Object, Object> getInformationTypeList(Integer user_id) {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<userInformation> list = new ArrayList<userInformation>();
			userInformation info1=informationMapper.getInformationIsNew(user_id,1);
			if(info1!=null){
				list.add(info1);
			}else{
				userInformation info11=new userInformation();
				info11.setInformationType("1");
				info11.setTypeName("资讯消息");
				list.add(info11);
			}
			userInformation info2=informationMapper.getInformationIsNew(user_id,2);
			if(info2!=null){
				list.add(info2);
			}else{
				userInformation info22=new userInformation();
				info22.setInformationType("2");
				info22.setTypeName("系统消息");
				list.add(info22);
			}
			userInformation info3=informationMapper.getInformationIsNew(user_id,3);
			if(info3!=null){
				list.add(info3);
			}else{
				userInformation info33=new userInformation();
				info33.setInformationType("3");
				info33.setTypeName("设备通知");
				list.add(info33);
			}
			userInformation info4=informationMapper.getInformationIsNew(user_id,4);
			if(info4!=null){
				list.add(info4);
			}else{
				userInformation info44=new userInformation();
				info44.setInformationType("4");
				info44.setTypeName("节水量通知");
				list.add(info44);
			}
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			map.put("resultMap", list);		
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
		
	}


	
	@Override
	public Map<Object, Object> getNewsInformationList() {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();

		try {
			List<NewsInformation> news = informationMapper.getNewsInformationList();
			LunboExample example=new LunboExample();
			List<Lunbo> slides  = lunboMapper.selectByExampleWithBLOBs(example);

			resultMap.put("slides", slides);
			resultMap.put("news", news);
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			map.put("resultMap", resultMap);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
		
	}



	@Override
	public Map<Object, Object> getDeviceInformationList() {
		// 返回前端map
	    Map<Object, Object> map = new HashMap<Object, Object>();
		try {
//			informationMapper.updateInformationStatus(BaseConstant.deviceInformation);
			
			List<Deviceinformation> list = new ArrayList<Deviceinformation>();
			list = informationMapper.getDeviceInformationList();
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			map.put("resultMap", list);				
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
		
	}

	@Override
	public Map<Object, Object> getInformationList(Integer user_id, Integer informationType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Map<String, Object> selectMap = new HashMap<>();
		try {
			List<Information> list = new ArrayList<>();
			selectMap.put("user_id", user_id);
			selectMap.put("informationType", informationType);
			list = informationMapper.getInformationList(selectMap);
			//获取过就把消息设置成已读
			for(Information info: list) {
				informationMapper.updateInformationStatus(info);
			}
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "获取成功");
			map.put("extra", null);
			map.put("resultMap", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}



	@Override
	public Map<Object, Object> clearInformations(Integer user_id,Integer information_id,Integer informationType) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			List<userInformation> ls=informationMapper.getInformationByType(informationType);
			for (userInformation i : ls) {
				InformationUser iu=new InformationUser();
				iu.setId(Long.valueOf(user_id));
				iu.setInformationId(Long.valueOf(i.getInformation_id()));
				informationUserMapper.insert(iu);
			}
			map.put("code", BaseConstant.appUserSuccessStatus);
			map.put("msg", "清空消息成功");
			map.put("extra", null);
			map.put("resultMap", null);
		return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code", BaseConstant.appUserErrorStatus);
			map.put("msg", "服务器异常");
			map.put("extra", null);
			map.put("resultMap", null);
			return map;
		}
	}
	
	@Override
	public Map<Object, Object> setInformationIsRead(Integer user_id, Integer informationId) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		InformationUser iu=new InformationUser();
		iu.setId(Long.valueOf(user_id));
		iu.setInformationId(Long.valueOf(informationId));
		informationUserMapper.insert(iu);
		map.put("code", BaseConstant.appUserSuccessStatus);
		map.put("msg", "成功");
		map.put("extra", null);
		map.put("resultMap", null);
		return map;
	}

	@Override
	public void addOrUpdateTbInformation(TbInformation info) {
		if(info.getId()!=null){//根据消息id去更新消息公用表
			TbInformationExample example=new TbInformationExample();
			example.createCriteria().andNewsIdEqualTo(Long.valueOf(info.getId()));
			info.setId(null);
			tbInformationMapper.updateByExampleSelective(info, example);
		}else{
			info.setCreatetime(new Date());
			tbInformationMapper.insertSelective(info);
		}
		
	}
	
}
