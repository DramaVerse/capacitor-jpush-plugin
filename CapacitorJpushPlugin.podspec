require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name = 'CapacitorJpushPlugin'
  s.version = package['version']
  s.summary = package['description']
  s.license = package['license']
  s.homepage = package['repository']['url']
  s.author = package['author']
  s.source = { :git => package['repository']['url'], :tag => s.version.to_s }
  s.source_files = 'ios/Sources/**/*.{swift,h,m,c,cc,mm,cpp}'
  s.ios.deployment_target = '14.0'
  s.dependency 'Capacitor'
  s.swift_version = '5.1'

  # JPush iOS SDK dependencies
  s.dependency 'JCore', '~> 3.0.4'
  s.dependency 'JPush', '~> 5.3.4'

  # Required frameworks
  s.frameworks = 'Foundation', 'UIKit', 'UserNotifications', 'CoreFoundation', 'CoreTelephony', 'SystemConfiguration', 'CoreGraphics', 'Security', 'CFNetwork', 'AdSupport'
  s.libraries = 'z', 'resolv'
end
