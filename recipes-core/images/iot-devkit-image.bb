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
IMAGE_INSTALL += "avahi libdns-sd avahi-autoipd"
IMAGE_INSTALL += "fuse-utils"
IMAGE_INSTALL += "connman connman-client connman-tests"
IMAGE_INSTALL += "tzdata"
IMAGE_INSTALL += "ca-certificates"
IMAGE_INSTALL += "icu"
IMAGE_INSTALL += "opencv"
IMAGE_INSTALL += "swig"
IMAGE_INSTALL += "lighttpd"

IMAGE_INSTALL += "mraa upm"
IMAGE_INSTALL += "timedate-scripts"
IMAGE_INSTALL += "wyliodrin-server-nodejs"
IMAGE_INSTALL += "iotkit-agent"

IMAGE_INSTALL += "packagegroup-core-eclipse-debug"

# these are the only lib32-* libs we want on our image
IMAGE_INSTALL += "lib32-uclibc lib32-uclibc-libm lib32-libstdc++ lib32-uclibc-libpthread"
# make sure no lib32-* libs get chosen by IMAGE_FEATURES
PACKAGE_EXCLUDE_COMPLEMENTARY = "lib32-.*"

ROOTFS_POSTPROCESS_COMMAND += "simlink_ld_uclibc ; install_repo ; simlink_node_modules; install_wyliodrin"

simlink_ld_uclibc() {
  # This allows uclibc compiled binaries to find the uclibc loader note that
  # binaries will not run unless LD_LIBRARY_PATH is set correctly
  cd ${IMAGE_ROOTFS}/lib/; ln -s ../lib32/ld-uClibc.so.0
}

install_repo() {
  echo "src mraa-upm http://iotdk.intel.com/repos/1.1/intelgalactic" > ${IMAGE_ROOTFS}/etc/opkg/mraa-upm.conf
}

simlink_node_modules() {
  # Create simlink form /usr/lib/node_modules/ to /usr/lib/node/ as different
  # people seem to want different paths
  cd ${IMAGE_ROOTFS}/usr/lib/; ln -s node_modules node
}

install_wyliodrin() {
  # Wyliodrin requires the boot partition to be mounted as /media/card
  install -d ${IMAGE_ROOTFS}/media/card
}

EXTRA_IMAGEDEPENDS = "grub-conf"
