import {StyleSheet, Text, TouchableOpacity, View} from 'react-native';
import React, {useState} from 'react';

export default () => {
  const [result, setResult] = useState<string>('扫码结果');

  const onButtonPress = () => {};

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
