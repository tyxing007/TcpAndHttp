package com.instrumenta;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

// һ������ Attach API ���߳�����
public class AttachThread extends Thread {
	public static void main(String[] args) throws InterruptedException {
		new AttachThread("E:\\instrument2\\TestInstrument1.jar", VirtualMachine.list()).start();

	}

	private List<VirtualMachineDescriptor> listBefore;

	private String jar;

	AttachThread(String attachJar, List<VirtualMachineDescriptor> vms) {
		listBefore = vms; // ��¼��������ʱ�� VM ����
		jar = attachJar;
	}

	public void run() {
		VirtualMachine vm = null;
		List<VirtualMachineDescriptor> listAfter = null;
		try {
			int count = 0;
			while (true) {
				listAfter = VirtualMachine.list();
				for (VirtualMachineDescriptor vmd : listAfter) {
					if (!listBefore.contains(vmd)) {
						// ��� VM �����ӣ����Ǿ���Ϊ�Ǳ���ص� VM ������
						// ��ʱ�����ǿ�ʼ������ VM
						vm = VirtualMachine.attach(vmd);
						break;
					}
				}
				Thread.sleep(800);
				count++;
				if (null != vm || count >= 10) {
					break;
				}
			}
			System.out.println("a:" + vm);
			vm.loadAgent(jar);
			vm.detach();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
