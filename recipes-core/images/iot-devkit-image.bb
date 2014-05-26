DESCRIPTION = "A fully functional image to be placed on an SD card"

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_LINGUAS = " "

LICENSE = "GPLv2"

IMAGE_FSTYPES = "ext3 live"

inherit core-image

IMAGE_ROOTFS_SIZE = "2048"

#IMAGE_FSTYPES_remove = "cpio.lzma"
NOISO = "1"
NOHDD = "1"
IMAGE_ROOTFS_SIZE = "307200"

EXTRA_IMAGECMD_append_ext2 = " -N 2000"

IMAGE_FEATURES += "package-management"
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "

IMAGE_INSTALL += "kernel-modules"
IMAGE_INSTALL += "ethtool"
IMAGE_INSTALL += "strace"
IMAGE_INSTALL += "ppp"
IMAGE_INSTALL += "linuxptp"
IMAGE_INSTALL += "libstdc++"
IMAGE_INSTALL += "sysstat"

IMAGE_INSTALL += "python python-modules python-numpy"
IMAGE_INSTALL += "alsa-lib alsa-utils"
IMAGE_INSTALL += "wireless-tools"
IMAGE_INSTALL += "wpa-supplicant"
IMAGE_INSTALL += "openssh"
IMAGE_INSTALL += "nodejs"

IMAGE_INSTALL += "linux-firmware-iwlwifi-6000g2a-6"
IMAGE_INSTALL += "linux-firmware-iwlwifi-135-6"
IMAGE_INSTALL += "bluez5"

IMAGE_INSTALL += "avahi libdns-sd"
IMAGE_INSTALL += "fuse-utils"
IMAGE_INSTALL += "connman connman-client"

IMAGE_INSTALL += "swig"
IMAGE_INSTALL += "lighttpd"

IMAGE_INSTALL += "packagegroup-core-eclipse-debug"

IMAGE_INSTALL += "lib32-uclibc lib32-uclibc-libm lib32-libstdc++"
IMAGE_INSTALL += "galileo-target"

ROOTFS_POSTPROCESS_COMMAND += "install_sketch ; "

install_sketch() {
  # Create /sketch directory required to run arduino sketches
  install -d ${IMAGE_ROOTFS}/sketch
  # This allows uclibc compiled binaries to find the uclibc loader note that
  # binaries will not run unless LD_LIBRARY_PATH is set correctly
  cd ${IMAGE_ROOTFS}/lib/; ln -s ../lib32/ld-uClibc.so.0
}

EXTRA_IMAGEDEPENDS = "grub-conf"
