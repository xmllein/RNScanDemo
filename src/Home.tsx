import {
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  NativeModules,
  PermissionsAndroid,
} from 'react-native';
import React, {useEffect, useState} from 'react';

export default () => {
  const [result, setResult] = useState<string>('扫码结果');

  // 申请权限
  useEffect(() => {
    requestCameraPermission();
  }, []);

  const requestCameraPermission = async () => {
    const checkResult = await PermissionsAndroid.check(
      PermissionsAndroid.PERMISSIONS.CAMERA,
    );
    if (checkResult) {
      console.log('已有相机权限');
      return;
    }

    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.CAMERA,
        {
          title: '申请相机权限',
          message: '需要相机权限才能扫码',
          buttonNeutral: '稍后询问',
          buttonNegative: '取消',
          buttonPositive: '确定',
        },
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        console.log('相机权限已获取');
      } else {
        console.log('相机权限被拒绝');
      }
    } catch (err) {
      console.warn(err);
    }
  };

  // 调用原生扫码模块
  const onButtonPress = async () => {
    // 找到原生模块（ScanModule）
    const {Scan} = NativeModules;
    // promise 调用方法 (推荐)
    setResult(await Scan.startScan());

    // callback 调用方法
    // Scan.startScan2((result: string) => {
    //   setResult(result);
    // });
  };

  return (
    <View style={styles.root}>
      <Text style={styles.title}>桥接原生扫描模块 </Text>
      <TouchableOpacity style={styles.button} onPress={onButtonPress}>
        <Text style={styles.buttonTxt}>跳转扫码</Text>
      </TouchableOpacity>
      <View style={styles.resultContainer}>
        <Text style={styles.resultTxt}>{result}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  root: {
    width: '100%',
    height: '100%',
    backgroundColor: 'white',
    flexDirection: 'column',
    alignItems: 'center',
    paddingHorizontal: 16,
  },
  title: {
    fontSize: 26,
    color: '#333',
    fontWeight: 'bold',
    marginTop: 48,
  },
  button: {
    width: 120,
    height: 36,
    backgroundColor: '#2030ff',
    borderRadius: 8,
    justifyContent: 'center',
    alignContent: 'center',
    marginTop: 32,
  },
  buttonTxt: {
    fontSize: 16,
    color: 'white',
    textAlign: 'center',
  },
  resultContainer: {
    width: '100%',
    height: 200,
    marginTop: 32,
    borderWidth: 1,
    borderColor: '#666',
    borderRadius: 8,
    borderStyle: 'dashed',
    justifyContent: 'center',
    alignContent: 'center',
    alignItems: 'center',
  },
  resultTxt: {
    fontSize: 18,
    color: '#666',
    textAlign: 'center',
  },
});
