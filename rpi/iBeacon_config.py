import os
import argparse

parser = argparse.ArgumentParser(description="Configures Raspberry Pi to act as an iBeacon.")
parser.add_argument('--rpi', help='Enter the RPi number in use.', type = int, action='store', dest='rpi', default=None, required=True)
args = parser.parse_args()

if args.rpi == 1:
	os.system("sudo hciconfig hci0 up")
	os.system("sudo hciconfig hci0 leadv 3")
	os.system("sudo hciconfig hci0 noscan")
	os.system("sudo hcitool -i hci0 cmd 0x08 0x0008 1E 02 01 1A 1A FF 4C 00 02 15 E2 0A 39 F4 73 F5 4B C4 A1 2F 17 D1 AD 07 A9 B5 02 71 01 2E C8 00")
elif args.rpi == 2:
	os.system("sudo hciconfig hci0 up")
	os.system("sudo hciconfig hci0 leadv 3")
	os.system("sudo hciconfig hci0 noscan")
	os.system("sudo hcitool -i hci0 cmd 0x08 0x0008 1E 02 01 1A 1A FF 4C 00 02 15 E2 0A 39 F4 73 F5 4B C4 A1 2F 17 D1 AD 07 A9 B5 03 CF 01 2F C8 00")