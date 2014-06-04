package com.tmd.libstools;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;

import com.tmd.deviceinfo.Deviceinfo;

public class DeviceData {

	ArrayList<HashMap<String, String>>	hashdevice	= new ArrayList<HashMap<String, String>>();

	public DeviceData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("MODEL", "LG-P880");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "720");
		map.put("previewsizeofheight", "1184");
		map.put("statusbarheight", "96");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "LG-P880");
		map.put("orentation", "landscape");
		map.put("setcamorentation", "0");
		map.put("previewsizeofwidth", "624");
		map.put("previewsizeofheight", "1280");
		map.put("statusbarheight", "96");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "LG-P880");
		map.put("orentation", "reverse_landscape");
		map.put("setcamorentation", "180");
		map.put("previewsizeofwidth", "624");
		map.put("previewsizeofheight", "1280");
		map.put("statusbarheight", "96");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-S5830");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "960");
		map.put("previewsizeofheight", "1265");
		map.put("statusbarheight", "25");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-S5830");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "960");
		map.put("previewsizeofheight", "1265");
		map.put("statusbarheight", "25");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-S5360");
		map.put("orentation", "landscape");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "320");
		map.put("previewsizeofheight", "240");
		map.put("statusbarheight", "19");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P3100");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "960");
		map.put("previewsizeofheight", "1265");
		map.put("statusbarheight", "25");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P7300B");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "775");
		map.put("previewsizeofheight", "1280");
		map.put("statusbarheight", "25");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P7300B");
		map.put("orentation", "landscape");
		map.put("setcamorentation", "BYPASS");
		map.put("previewsizeofwidth", "775");
		map.put("previewsizeofheight", "1280");
		map.put("statusbarheight", "25");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map.put("MODEL", "GT-S7562");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "480");
		map.put("previewsizeofheight", "762");
		map.put("statusbarheight", "38");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P7500");
		map.put("orentation", "reverse_landscape");
		map.put("setcamorentation", "270");
		map.put("previewsizeofwidth", "800");
		map.put("previewsizeofheight", "1232");
		map.put("statusbarheight", "48");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map.put("MODEL", "GT-I9100");
		map.put("orentation", "portrait");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "480");
		map.put("previewsizeofheight", "762");
		map.put("statusbarheight", "38");
		map.put("picturerotate", "-270");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P6800");
		map.put("orentation", "reverse_landscape");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "800");
		map.put("previewsizeofheight", "1232");
		map.put("statusbarheight", "48");
		map.put("picturerotate", "90");
		hashdevice.add(map);

		map = new HashMap<String, String>();
		map.put("MODEL", "GT-P7500");
		map.put("orentation", "reverse_landscape");
		map.put("setcamorentation", "90");
		map.put("previewsizeofwidth", "800");
		map.put("previewsizeofheight", "1232");
		map.put("statusbarheight", "48");
		map.put("picturerotate", "90");
		hashdevice.add(map);

	}

	public String[] getDeviceCamSetting(String Model, String orentation, Activity ac) {
		String[] deviceinfo = new String[7];
		int countdata = 0;
		for (int i = 0; i < hashdevice.size(); i++) {
			if (hashdevice.get(i).get("MODEL").equals(Model) && hashdevice.get(i).get("orentation").equals(orentation)) {
				countdata = countdata + 1;
				deviceinfo[0] = hashdevice.get(i).get("MODEL");
				deviceinfo[1] = hashdevice.get(i).get("orentation");
				deviceinfo[2] = hashdevice.get(i).get("setcamorentation");
				deviceinfo[3] = hashdevice.get(i).get("previewsizeofwidth");
				deviceinfo[4] = hashdevice.get(i).get("previewsizeofheight");
				deviceinfo[5] = hashdevice.get(i).get("statusbarheight");
				deviceinfo[6] = hashdevice.get(i).get("picturerotate");
				break;
			}
		}
		if (countdata <= 0 && ("portrait").equals(orentation)) {
			deviceinfo[0] = ("Default");
			deviceinfo[1] = ("portrait");
			deviceinfo[2] = ("90");
			deviceinfo[3] = "" + (Deviceinfo.getDeviceScreenSize(ac)[0]);
			deviceinfo[4] = "" + (Deviceinfo.getDeviceScreenSize(ac)[1]);
			deviceinfo[5] = ("0");
			deviceinfo[6] = ("90");
		}

		if (countdata <= 0 && ("reverse_landscape").equals(orentation)) {
			deviceinfo[0] = ("Default");
			deviceinfo[1] = ("reverse_landscape");
			deviceinfo[2] = ("90");
			deviceinfo[3] = "" + (Deviceinfo.getDeviceScreenSize(ac)[0]);
			deviceinfo[4] = "" + (Deviceinfo.getDeviceScreenSize(ac)[1]);
			deviceinfo[5] = ("0");
			deviceinfo[6] = ("90");
		}

		return deviceinfo;

	}
}
