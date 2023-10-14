import {StyleSheet, Text, View} from 'react-native';
import React, {useState} from 'react';
import RNScanView from './components/RNScanView';

export default () => {
  const [result, setResult] = useState<string>('扫码结果');

  return (
    <View style={styles.root}>
      <Text style={styles.title}>桥接原生扫描组件 </Text>
      <RNScanView
        onQrcodeResult={(qrcode: string) => {
          setResult(qrcode);
        }}
      />
      <Text style={styles.resultTxt}>{result}</Text>
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

  resultTxt: {
    position: 'absolute',
    bottom: 100,
    fontSize: 18,
    color: '#666',
    textAlign: 'center',
  },
});
